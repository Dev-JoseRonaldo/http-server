package com.joseronaldo.http;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HttpParserTest {

    private HttpParser httpParser;

    @BeforeAll
    public void beforeClass() {
        httpParser = new HttpParser();
    }

    @Test
    void parserHttpRequest() {
        HttpRequest request = null;
        try {
            request = httpParser.parserHttpRequest(generateValidGETTestCase());
        } catch (HttpParsingException e) {
            fail(e);
        }

        assertEquals(request.getMethod(), HttpMethod.GET);
    }

    @Test
    void parserHttpRequestBadMethod1() {
        try {
            HttpRequest request = httpParser.parserHttpRequest(generateBadTestCaseMethodNumber1());
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
        }
    }

    private InputStream generateValidGETTestCase() {
        String rawData = "GET / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Cache-Control: max-age=0\r\n" +
                "sec-ch-ua: \"Google Chrome\";v=\"129\", \"Not=A?Brand\";v=\"8\", \"Chromium\";v=\"129\"\r\n" +
                "sec-ch-ua-mobile: ?0\r\n" +
                "sec-ch-ua-platform: \"Linux\"\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\r\n" +
                "Sec-Fetch-Site: none\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Sec-Fetch-User: ?1\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br, zstd\r\n" +
                "Accept-Language: pt-PT,pt;q=0.9,en-US;q=0.8,en;q=0.7\r\n" +
                "Cookie: _ga=GA1.1.1663642828.1699555627; ext_name=ojplmecpdpgccookcobabopnaifgidhf; _ga_6Y2NMENGVK=GS1.1.1724268055.14.0.1724268055.0.0.0; _ga_4RP82R15QX=GS1.1.1724267353.38.1.1724268827.60.0.0\r\n" +
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(StandardCharsets.US_ASCII)
        );

        return inputStream;
    }

    private InputStream generateBadTestCaseMethodNumber1() {
        String rawData = "GeT / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Cache-Control: max-age=0\r\n" +
                "sec-ch-ua: \"Google Chrome\";v=\"129\", \"Not=A?Brand\";v=\"8\", \"Chromium\";v=\"129\"\r\n" +
                "sec-ch-ua-mobile: ?0\r\n" +
                "sec-ch-ua-platform: \"Linux\"\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\r\n" +
                "Sec-Fetch-Site: none\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Sec-Fetch-User: ?1\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br, zstd\r\n" +
                "Accept-Language: pt-PT,pt;q=0.9,en-US;q=0.8,en;q=0.7\r\n" +
                "Cookie: _ga=GA1.1.1663642828.1699555627; ext_name=ojplmecpdpgccookcobabopnaifgidhf; _ga_6Y2NMENGVK=GS1.1.1724268055.14.0.1724268055.0.0.0; _ga_4RP82R15QX=GS1.1.1724267353.38.1.1724268827.60.0.0\r\n" +
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(StandardCharsets.US_ASCII)
        );

        return inputStream;
    }
}