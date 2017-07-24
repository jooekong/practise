package com.ericsson.eliokog.jzof;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by eliokog on 2017/4/7.
 */
public class SocketTest {


    public static class Server {
        ServerSocket server;

        public Server(int port) throws IOException {
            server = new ServerSocket(port);
            Socket socket = server.accept();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                System.out.println(reader.readLine());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(() -> {
            try {
                Server server = new Server(2222);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        Socket socket = new Socket("127.0.0.1", 2222);
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            writer.write(reader.readLine());

        }

    }
}


