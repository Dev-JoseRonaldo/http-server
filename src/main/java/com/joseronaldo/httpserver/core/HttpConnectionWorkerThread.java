package com.joseronaldo.httpserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread{

    private Socket socket;
    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = socket.getInputStream(); // Get input stream from the client
            outputStream = socket.getOutputStream(); // Get output stream to respond to the client

//            // Request Logger
//            int _byte;
//
//            while ((_byte = inputStream.read()) >= 0) {
//                System.out.print((char) _byte);
//            }

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



            System.out.println("Connection Processing Finished.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problem with communication -> " + e.toString());
        } finally {
            // Closing streams and socket after response is sent
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignored) {}
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ignored) {}
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ignored) {}
            }
        }
    }
}
