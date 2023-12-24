package service.com.tinyurl.handler;


import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import service.com.tinyurl.UrlShorteningApiResponse;
import service.com.tinyurl.ctrl.RedirectController;

@RestController
@Log
public class RedirectHandler {


    @GetMapping(value = "/{shortened}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> redirectUrl(@PathVariable("shortened") String shortened) {

        log.info("redirect url request start");

        UrlShorteningApiResponse response = RedirectController.run(shortened) ;

        log.info("redirect url request ends");

        if (response.errors != null)
            return new ResponseEntity<>(response.errors, response.errors.getStatusCode());
        return new ResponseEntity<>(response.success, response.successCode);
    }
}
