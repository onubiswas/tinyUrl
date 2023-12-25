package service.com.tinyurl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import service.com.tinyurl.model.UrlMapping;

import java.util.Optional;

public interface UrlEntityRepository extends JpaRepository<UrlMapping, String> {
    Optional<UrlMapping> findByOriginalUrl(String url);
    Optional<UrlMapping> findByShortenedUrl(String url);

}
