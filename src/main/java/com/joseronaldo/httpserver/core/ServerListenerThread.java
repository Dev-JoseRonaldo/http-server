package com.joseronaldo.httpserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread{

    private int port;
    private String webroot;
    private ServerSocket serverSocket;

    public ServerListenerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        try {

            Socket socket = serverSocket.accept(); // Accepts a client connection

            InputStream inputStream = socket.getInputStream(); // Get input stream from the client
            OutputStream outputStream = socket.getOutputStream(); // Get output stream to respond to the client

            // HTML content to be sent in the HTTP response body
            String html =
                    "<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                            "    <title>HTTP Server</title>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "    <h1>Welcome to the HTTP server!</h1>\n" +
                            "    <p>This is a simple HTTP server.</p>\n" +
                            "</body>\n" +
                            "</html>";

            final String CRLF = "\n\r"; // ASCII = 13 and 10

            // HTTP response
            String response =
                    "HTTP/1.1 200 OK" + CRLF +  // Status Line : HTTP_VERSION RESPONSE_CODE RESPONSE_MESSAGE
                            "Content-Length: " + html.getBytes().length + CRLF + // HEADER
                            CRLF +
                            html + // BODY
                            CRLF + CRLF;

            outputStream.write(response.getBytes()); // Send the response to the client

            // Closing streams and socket after response is sent
            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
