package com.joseronaldo.http;

public class HttpRequest extends HttpMessage{

    private HttpMethod method;
    private String requestTarget;
    private String httpVersion;

    HttpRequest() {
    }

    public HttpMethod getMethod() {
        return method;
    }

    void setMethod(String methodName) throws HttpParsingException {
        for (HttpMethod method: HttpMethod.values()) {
            if (methodName.equals(method.name())) {
                this.method = HttpMethod.valueOf(methodName);
                return;
            }
        }

        throw new HttpParsingException(
                HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED
        );

    }
}
