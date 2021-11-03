package ru.job4j.chapter1.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoSocked {

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String line = in.readLine(); line != null && !line.isEmpty(); line = in.readLine()) {
                        System.out.println(line);
                        if (line.contains("?msg=Bye")) {
                            server.close();
                        }
                    }
                    out.flush();
                }
            }
        }
    }
}
