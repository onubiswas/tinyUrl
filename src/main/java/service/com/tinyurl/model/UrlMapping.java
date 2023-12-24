package service.com.tinyurl.model;

import jdk.jfr.DataAmount;
import lombok.Data;

@Data
public class UrlMapping {
    String id;
    String originalUrl;
    String shortenedUrl;
    String cratedAt;
    String expiredAt;
}
