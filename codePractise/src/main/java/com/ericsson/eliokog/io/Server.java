package com.ericsson.eliokog.io;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by eliokog on 2016/10/9.
 */
public class Server {

    public static void main(String[] arg0){
        new Server(3334).start();
    }

    ServerSocket server = null;

    public Server(int port) {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("failed to start the socket server, error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void start() {
                try {
                    Socket s = server.accept();
                    System.out.println("Connection established");
                    ObjectInputStream is = new ObjectInputStream(s.getInputStream());
                    Echo echo = (Echo) is.readObject();
                    echo.echo();
                    echo.setMsg("Server Echo");
                    System.out.println("Server Echo");
                    ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
                    os.writeObject(echo);
                    os.flush();

                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            }

    }
