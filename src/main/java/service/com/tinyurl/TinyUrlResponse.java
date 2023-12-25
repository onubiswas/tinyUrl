package service.com.tinyurl;

import lombok.Data;

@Data
public class TinyUrlResponse {
    String originalUrl;

    String shortenedUrl;

    String message;
}