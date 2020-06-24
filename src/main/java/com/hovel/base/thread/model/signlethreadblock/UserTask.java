package com.hovel.base.thread.model.signlethreadblock;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

public class UserTask implements Runnable {

    @Override
    public void run() {
        int port = 8081;
        try {
            Socket socket = new Socket("127.0.0.1", port);
            System.out.println(Thread.currentThread().getName() + "请求开始...");
            OutputStream outputStream = socket.getOutputStream();
            Writer writer = new OutputStreamWriter(outputStream);
            writer.write("I am the request by " + Thread.currentThread().getName());
            writer.flush();
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "请求结束...");
        }

    }
}
