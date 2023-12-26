package service.com.tinyurl.handler;


import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.com.tinyurl.TinyUrlResponse;
import service.com.tinyurl.ctrl.UrlShorteningCtrl;

import java.security.NoSuchAlgorithmException;

@Log
@RestController
public class UrlShorteningHandler {


    @Autowired
    UrlShorteningCtrl urlShorteningCtrl;
    @PostMapping(value = "/shorten",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> shortening(@RequestBody UrlShorteningCtrl.UrlRequest body) throws NoSuchAlgorithmException {

        log.info("Url shortening request start");

        TinyUrlResponse response = urlShorteningCtrl.run(body);

        log.info("Url shortening request ends");

        if (response == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } else {
            return ResponseEntity.ok(response);
        }
    }
}
