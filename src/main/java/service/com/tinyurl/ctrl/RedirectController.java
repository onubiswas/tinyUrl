package service.com.tinyurl.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.com.tinyurl.TinyUrlResponse;
import service.com.tinyurl.dao.UrlEntityRepository;
import service.com.tinyurl.model.UrlMapping;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import java.util.Optional;

@Component
public class RedirectController {

    @Autowired
    UrlEntityRepository repository;
    public TinyUrlResponse run(String shortened) {

        TinyUrlResponse response = new TinyUrlResponse();

        // search in db shortened available or not
        try {
            Optional<UrlMapping> opt = repository.findByShortenedUrl(shortened);
            if (opt.isPresent()) {
                UrlMapping urlMapping = opt.get();

                response.setOriginalUrl(urlMapping.getOriginalUrl());
                response.setShortenedUrl(urlMapping.getShortenedUrl());
                response.setMessage("success");
            } else {
                response.setMessage("No entity is found for the given URL");
            }

        } catch (IncorrectResultSizeDataAccessException ie) {

            response.setMessage("Query did not return a unique result");
            // particular exception
        } catch (Exception e) {
            // global exception

        }
        return response;

    }
}
