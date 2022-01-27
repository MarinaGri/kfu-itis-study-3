package ru.itis.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalcWebServer {
    private final int PORT = 8080;
    private final String FIRST_LINE = "^get /.+http/1\\.1$";
    private final String PARAM = "a=([0-9]+[.,]?[0-9]*)&b=([0-9]+[.,]?[0-9]*)";
    private final String INVALID_PARAM_MESSAGE = "Parameters format: a=<number>&b=<number>";
    private ServerSocket server;

    public void start() throws ServerException {
        try {
            server = new ServerSocket(PORT);
            while (true) {
                Socket socket = server.accept();
                try(BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String str = br.readLine();
                    if (str != null && checkRequest(URLDecoder.decode(str.toLowerCase(), StandardCharsets.UTF_8))){
                        send(str, socket);
                    } else {
                        socket.close();
                    }
                } catch (IOException ex){
                    throw new ServerException("Failed to read request", ex);
                }
            }
        } catch (IOException ex) {
            throw new ServerException("Failed to start server", ex);
        }
    }

    private boolean checkRequest(String str) {
        Pattern pattern = Pattern.compile(FIRST_LINE);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    private String[] parse(String str) {
        String[] param = new String[2];
        Pattern pattern = Pattern.compile(PARAM);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            for (int i = 0; i < param.length; i++) {
                param[i] = matcher.group(i + 1).replace(',','.');
            }
        }
        return param;
    }

    private void send(String str, Socket socket) throws ServerException {
        String[] param = parse(URLDecoder.decode(str.toLowerCase(), StandardCharsets.UTF_8));
        try(PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            if (param[0] != null && param[1] != null) {
                sendResultResponse(printWriter, calc(param[0], param[1]));
            } else {
                sendInvalidDataResponse(printWriter);
            }
        } catch (IOException ex){
            throw new ServerException("Failed to send response", ex);
        }
    }

    private double calc(String a, String b) {
        return Double.parseDouble(a) + Double.parseDouble(b);
    }

    private void sendResultResponse(PrintWriter printWriter, double n) {
        double res = Math.round(n * 100.0) / 100.0;
        sendResponse(printWriter, String.valueOf(res));
    }

    private void sendInvalidDataResponse(PrintWriter printWriter) {
        sendResponse(printWriter, INVALID_PARAM_MESSAGE);
    }

    private void sendResponse(PrintWriter printWriter, String content) {
        printWriter.print("HTTP/1.1 200 OK\r\n");
        printWriter.print("Content-Type: text/plain;charset=utf-8\r\n");
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        printWriter.print("Content-Length: " + bytes.length + "\r\n\r\n");
        printWriter.print(content);
        printWriter.flush();
    }

    public int getPORT() {
        return PORT;
    }
}
