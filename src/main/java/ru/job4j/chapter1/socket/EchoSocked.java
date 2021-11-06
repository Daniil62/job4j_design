package ru.job4j.chapter1.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.chapter1.logging.UsageLog4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoSocked {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public void connection() throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    out.write(MessageStore.RESPONSE_SUCCESSFUL_CONNECTION_MSG.getBytes());
                    StringBuilder builder = new StringBuilder();
                    for (String line = in.readLine(); line != null && !line.isEmpty(); line = in.readLine()) {
                        if (line.contains(MessageStore.REQUEST_EXIT_SEQUENCE)) {
                            server.close();
                            break;
                        }
                        builder.append(line);
                    }
                    sendResponse(out, builder.toString());
                    out.flush();
                }
            }
        }
    }

    private void sendResponse(OutputStream out, String request) throws IOException {
        if (request != null && !request.isEmpty()) {
            if (request.contains(MessageStore.REQUEST_GREETING_SEQUENCE)) {
                out.write(MessageStore.RESPONSE_GREETING_MSG.getBytes());
            } else {
                out.write(MessageStore.RESPONSE_DEFAULT_MSG.getBytes());
            }
        }
    }

    private static class MessageStore {

        private static final String RESPONSE_SUCCESSFUL_CONNECTION_MSG = "HTTP/1.1 200 OK\r\n\r\n";
        private static final String RESPONSE_GREETING_MSG = "Hello, dear friend.\r\n\r\n";
        private static final String RESPONSE_DEFAULT_MSG = "What? \r\n\r\n";
        private static final String REQUEST_GREETING_SEQUENCE = "?msg=Hello";
        private static final String REQUEST_EXIT_SEQUENCE = "?msg=Exit";
    }

    public static void main(String[] args) {
        try {
            new EchoSocked().connection();
        } catch (IOException e) {
            EchoSocked.LOG.error("Error in EchoSocked: ", e);
        }
    }
}
