package com.joseronaldo.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpParser {

    private static final int SP = 0x20; // ASCII code 32
    private static final int CR = 0x0D; // ASCII code 13
    private static final int LF = 0x0A; // ASCII code 10

    public HttpRequest parserHttpRequest(InputStream inputStream) {
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);

        HttpRequest request = new HttpRequest();

        try {
            parseRequestLine(reader, request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        parseHeaders(reader, request);
        parseBody(reader, request);

        return request;
    }

    private void parseRequestLine(InputStreamReader reader, HttpRequest request) throws IOException {
        StringBuffer processingDataBuffer = new StringBuffer();

        boolean methodParsed = false;
        boolean requestTargetParsed = false;

        int _byte;
        while ((_byte = reader.read()) >= 0) {
            if (_byte == CR) {
                _byte = reader.read();
                if (_byte == LF) {
                    System.out.println("Resquest Line - HTTP VERSION: " + processingDataBuffer.toString());
                    return;
                }
            }

            if (_byte == SP) {

                if (!methodParsed) {
                    System.out.println("Resquest Line - METHOD: " + processingDataBuffer.toString());
                    request.setMethod(processingDataBuffer.toString());
                    methodParsed = true;
                } else if (!requestTargetParsed) {
                    System.out.println("Resquest Line - REQUEST TARGET: " + processingDataBuffer.toString());
                    requestTargetParsed = true;
                }

                processingDataBuffer.delete(0,processingDataBuffer.length());
            } else {
                processingDataBuffer.append((char) _byte);
            }
        }

    }

    private void parseHeaders(InputStreamReader reader, HttpRequest request) {
    }

    private void parseBody(InputStreamReader reader, HttpRequest request) {
    }
}
