package com.joseronaldo.httpserver;

import com.joseronaldo.httpserver.config.Configuration;
import com.joseronaldo.httpserver.config.ConfigurationManager;
import com.joseronaldo.httpserver.core.ServerListenerThread;

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
            ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
            serverListenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
