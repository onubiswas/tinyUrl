package service.com.tinyurl.handler;


import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.com.tinyurl.UrlShorteningApiResponse;
import service.com.tinyurl.ctrl.UrlShorteningCtrl;

@Log
@RestController
public class UrlShorteningHandler {


    @Autowired
    UrlShorteningCtrl urlShorteningCtrl;
    @PostMapping(value = "/{originalUrl}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> shortening(@PathVariable("originalUrl") String originalUrl) {

        log.info("Url shortening request start");

        UrlShorteningApiResponse response = urlShorteningCtrl.run(originalUrl);

        log.info("Url shortening request ends");

        if (response.errors != null)
            return new ResponseEntity<>(response.errors, response.errors.getStatusCode());
        return new ResponseEntity<>(response.success, response.successCode);
    }
}
