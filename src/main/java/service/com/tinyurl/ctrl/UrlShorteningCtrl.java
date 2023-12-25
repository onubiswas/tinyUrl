package service.com.tinyurl.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.com.tinyurl.TinyUrlResponse;
import service.com.tinyurl.dao.UrlEntityRepository;
import service.com.tinyurl.model.UrlMapping;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Component
public class UrlShorteningCtrl {

    @Autowired
    UrlEntityRepository repository;

    public TinyUrlResponse run(String originalUrl) throws NoSuchAlgorithmException {

        // TODO : Validate the originalUrl

        TinyUrlResponse response = new TinyUrlResponse();

        UrlMapping urlMapping = new UrlMapping();

        // fetch from db for shortened url
        Optional<UrlMapping> res = repository.findByOriginalUrl(originalUrl);

        if (!res.isPresent()) {
            String generateShortenedUrl = generateUrl(originalUrl);
            if(generateShortenedUrl == "Error") {
                response.setMessage("Error while generating shortened url");
                return response;
            }

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

    private String generateUrl(String originalUrl) throws NoSuchAlgorithmException {
        try {
            // Create a SHA-256 hash of the original URL
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(originalUrl.getBytes(StandardCharsets.UTF_8));

            // Convert the hash bytes to a Base64-encoded string
            String base64Hash = Base64.getUrlEncoder().encodeToString(hashBytes);

            // Return a substring of the base64Hash as the shortened URL
            return base64Hash.substring(0, 8); // Adjust length as needed

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Handle the exception or return a default value
            return "Error";
        }
    }

    public class UrlRequest {
        String url;
    }
}
