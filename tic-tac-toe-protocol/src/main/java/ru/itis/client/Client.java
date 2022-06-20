package ru.itis.client;


import ru.itis.protocol.Protocol;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private InetAddress inetAddress;
    private Integer num;
    private Boolean value;

    public Client(String host) throws IOException {
        this.inetAddress = InetAddress.getByName(host);
    }

    public char[] send() throws IOException {
        char[] res = new char[10];
        try (Socket socket = new Socket(inetAddress, Protocol.PORT)) {
            OutputStream os = socket.getOutputStream();
            if (num == null || value == null) {
                throw new IllegalStateException("num or value is null");
            }
            os.write(num);
            os.write(value ? 1 : 0);

            os.flush();

            InputStream in = socket.getInputStream();
            int b;
            int i = 0;
            while ((b = in.read()) != 127) {
                if (b == 3) {
                    res[i++] = '-';
                } else {
                    res[i++] = b == 1 ? 'X' : '0';
                }
            }
        }
        return res;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
