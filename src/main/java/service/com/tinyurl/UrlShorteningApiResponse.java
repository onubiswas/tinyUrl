package service.com.tinyurl;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class UrlShorteningApiResponse {
    public Object success;
    public HttpStatusCode successCode = HttpStatus.OK;
    public UrlShorteningErrorResponse errors;

    public UrlShorteningApiResponse(Object success) {
        this.success = success;
    }
    public UrlShorteningApiResponse(UrlShorteningErrorResponse errors) {
        this.errors = errors;
    }
}