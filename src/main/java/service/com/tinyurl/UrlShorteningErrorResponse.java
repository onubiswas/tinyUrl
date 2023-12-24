package service.com.tinyurl;

import org.springframework.http.HttpStatusCode;

import java.util.HashMap;

public class UrlShorteningErrorResponse {
    private String message;
    private HashMap<String, String> errors;
    private int appcode;
    private HttpStatusCode statusCode;

    UrlShorteningErrorResponse(String message, HashMap<String, String> errors, int appcode, HttpStatusCode statusCode) {
        this.statusCode = statusCode;
        this.message = message;
        this.appcode = appcode;
        this.errors = errors;
    }

    public HttpStatusCode getStatusCode() {
        return this.statusCode;
    }

}
