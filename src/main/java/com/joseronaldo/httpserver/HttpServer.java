package com.joseronaldo.httpserver;

import com.joseronaldo.httpserver.config.Configuration;
import com.joseronaldo.httpserver.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * Driver Class for http server
 *
 */

public class HttpServer {
    public static void main(String[] args){

        System.out.println("Server starting...");

        // Load configuration file for server
        ConfigurationManager.getInstance().localConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Using Port: " + conf.getPort());
        System.out.println("Using WebRoot: " + conf.getWebroot());

        try {
            // Create a server socket to listen for client connections on the specified port
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
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
