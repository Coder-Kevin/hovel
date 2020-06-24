package com.hovel.base.thread.model.signlethreadblock;

import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

public class TomcatServer {

    @SneakyThrows
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;

        try {
            serverSocket = new ServerSocket(8081);
            while (true) {
                socket = serverSocket.accept();
                System.out.print("客户端连接....");
                inputStream = socket.getInputStream();
                Reader reader = new InputStreamReader(inputStream);
                char[] chars = new char[1024];
                int len = 0;
                StringBuilder result = new StringBuilder();
                while ((len = reader.read(chars, 0, len)) != -1) {
                    result.append(chars);
                }

                System.out.println(result);

                Thread.sleep(3000);
                System.out.println(result + " sleep 3s done");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
