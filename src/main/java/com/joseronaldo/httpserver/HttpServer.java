package com.joseronaldo.httpserver;

import com.joseronaldo.httpserver.config.Configuration;
import com.joseronaldo.httpserver.config.ConfigurationManager;

/**
 *
 * Driver Class for http server
 *
 */

public class HttpServer {
    public static void main(String[] args){

        System.out.println("Server starting...");

        ConfigurationManager.getInstance().localConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Using Port: " + conf.getPort());
        System.out.println("Using WebRoot: " + conf.getWebroot());
    }
}
