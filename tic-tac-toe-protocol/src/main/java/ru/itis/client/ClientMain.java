package ru.itis.client;

import java.io.IOException;
import java.util.Random;

public class ClientMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        Client client = new Client("localhost");
        Random random = new Random();
        while (true) {
            Thread.sleep(1500);
            client.setNum(random.nextInt(9));
            client.setValue(random.nextBoolean());
            char[] res = client.send();
            for (int i = 0; i < res.length - 1; i++) {
                if (i % 3 == 0) {
                    System.out.println();
                }
                System.out.print(res[i] + " ");
            }
            System.out.println();
        }
    }
}
