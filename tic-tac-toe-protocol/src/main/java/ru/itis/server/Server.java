package ru.itis.server;


import ru.itis.protocol.Protocol;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {
    private ServerSocket server;
    private Boolean[] field;
    private byte last;

    public Server() throws IOException {
        this.server = new ServerSocket(Protocol.PORT);
        this.field = new Boolean[9];
        this.last = 3;
    }

    public void start() throws IOException {
        while (true) {
            Socket socket = server.accept();
            InputStream in = socket.getInputStream();
            byte num;
            byte value;
            if ((num = (byte) in.read()) != -1 && (value = (byte) in.read()) != -1) {
                if (field[num] == null) {
                    if (last == 3 || value != last) {
                        last = value;
                        field[num] = value == 1;
                    }
                }
                OutputStream os = socket.getOutputStream();
                sendResponse(os);
                socket.close();
            }
        }
    }

    private boolean isEnd() {
        for (int i = 0; i < field.length; i += 3) {
            if (field[i] != null && field[i] == field[i + 1] && field[i] == field[i + 2]) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i] != null && field[i] == field[i + 3] && field[i] == field[i + 6]) {
                return true;
            }
        }
        if (field[0] != null && field[0] == field[4] && field[0] == field[8]) {
            return true;
        }
        return field[2] != null && field[2] == field[4] && field[2] == field[6];
    }

    private void sendResponse(OutputStream os) throws IOException {
        boolean result = isEnd() || isFill();
        for (Boolean t : field) {
            byte res;
            if (t == null) {
                res = 3;
            } else {
                res = (byte) (t ? 1 : 0);
            }
            os.write(res);
        }
        if (result) {
            last = 3;
            Arrays.fill(field, null);
        }
        os.write(result ? 1 : 0);
        os.write(127);
        os.flush();
    }

    private boolean isFill() {
        for (Boolean t : field) {
            if (t == null) return false;
        }
        return true;
    }
}
