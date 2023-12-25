package service.com.tinyurl.model;

import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity
@Table(name = "url_mapping")
public class UrlMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String originalUrl;
    String shortenedUrl;
    String createdAt;
    String expiredAt;
}
