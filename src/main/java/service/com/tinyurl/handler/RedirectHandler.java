package service.com.tinyurl.handler;


import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import service.com.tinyurl.TinyUrlResponse;
import service.com.tinyurl.ctrl.RedirectController;

@RestController
@Log
public class RedirectHandler {

    @Autowired
    RedirectController redirectController;


    @GetMapping(value = "/{shortened}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> redirectUrl(@PathVariable("shortened") String shortened) {

        log.info("redirect url request start");

        TinyUrlResponse response = redirectController.run(shortened) ;

        log.info("redirect url request ends");

        if (response == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } else {
            return ResponseEntity.ok(response);
        }
    }
}
