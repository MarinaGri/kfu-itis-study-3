package ru.itis.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WebClient {
    private final String CONTENT_LENGTH_REG = "Content-Length: ([0-9]+)";
    private final String host;
    private final int port;
    private String method;
    private String path;
    private String getParameters;
    private String postParameters;
    private String mimeType;

    public WebClient(String host, int port){
        this.host = host;
        this.port = port;
        this.method = "GET";
        this.path = "/";
        this.getParameters = "";
        this.postParameters = "";
        this.mimeType = "text/plain";
    }

    public String send(){
        InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getByName(host);
        } catch (UnknownHostException ex) {
            return "Unknown host";
        }

        if(!(method.equals("GET") || method.equals("POST"))){
            return "Only POST and GET requests can be sent";
        }

        try(Socket socket = new Socket(inetAddress, port)) {
            try(PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) {

                printWriter.print(method + " " + path + "?" + getParameters + " HTTP/1.1\r\n");
                printWriter.print("HOST:" + inetAddress.getHostName() + "\r\n");

                if (method.equals("POST") && !postParameters.equals("")) {
                    byte[] bytes = postParameters.getBytes(StandardCharsets.UTF_8);
                    printWriter.print("Content-Type: " + mimeType + "\r\n");
                    printWriter.print("Content-length: " + bytes.length + "\r\n");
                }

                printWriter.print("\r\n");

                if (method.equals("POST")) {
                    printWriter.print(postParameters);
                }

                printWriter.flush();

                return read(socket.getInputStream());
            }
        } catch (IOException ex) {
            return "Connection lost";
        }
    }

    private String read(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader r = new BufferedReader(new InputStreamReader(inputStream))){
            String line;
            int length = 0;
            Pattern pattern = Pattern.compile(CONTENT_LENGTH_REG);
            while ((line = r.readLine()) != null && (!"".equals(line))) {
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()){
                    length = Integer.parseInt(matcher.group(1));
                }
                stringBuilder.append(line).append("\n");
            }

            stringBuilder.append("\n");

            for(int i = 0; i < length; i++){
                stringBuilder.append((char)r.read());
            }
        }
        return stringBuilder.toString();
    }

    private String setParams(Map<String, String> params){
        params = params.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        v -> URLEncoder.encode(v.getValue(), StandardCharsets.UTF_8)));

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        return sb.toString();
    }

    public void setMethod(String method) {
        this.method = method.toUpperCase();
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setGetParameters(Map<String, String> getParams) {
        this.getParameters = setParams(getParams);
    }

    public void setPostParameters(Map<String, String> postParams) {
        this.postParameters = setParams(postParams);
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
