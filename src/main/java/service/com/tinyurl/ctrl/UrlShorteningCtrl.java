package service.com.tinyurl.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.com.tinyurl.TinyUrlResponse;
import service.com.tinyurl.dao.UrlEntityRepository;
import service.com.tinyurl.model.UrlMapping;

import java.util.Optional;
import java.util.UUID;

@Component
public class UrlShorteningCtrl {

    @Autowired
    UrlEntityRepository repository;

    public TinyUrlResponse run(String originalUrl) {

        // TODO : Validate the originalUrl

        TinyUrlResponse response = new TinyUrlResponse();

        UrlMapping urlMapping = new UrlMapping();

        // fetch from db for shortened url
        Optional<UrlMapping> res = repository.findByOriginalUrl(originalUrl);

        if (!res.isPresent()) {
            String generateShortenedUrl = generateUrl(originalUrl);

            UrlMapping umapping = new UrlMapping();
            umapping.setShortenedUrl(generateShortenedUrl);
            umapping.setOriginalUrl(originalUrl);
            umapping.setCreatedAt(String.valueOf(System.currentTimeMillis()));
            umapping.setExpiredAt(String.valueOf(System.currentTimeMillis() + 30 * 24 * 60 * 60 * 1000));

            Optional<UrlMapping> savedMapping = Optional.ofNullable(repository.save(umapping));

            if (!savedMapping.isPresent()) {
                response.setMessage("Error saving the shortened URL");
                return response;
            } else {
                urlMapping = savedMapping.get();
            }
        } else {
            urlMapping = res.get();
        }
        response.setShortenedUrl(urlMapping.getShortenedUrl());
        response.setOriginalUrl(urlMapping.getOriginalUrl());
        response.setMessage("success");
        return response;
    }

    private String generateUrl(String originalUrl) {

        return "xyz";
    }

    public class UrlRequest {
        String url;
    }
}
