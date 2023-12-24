package service.com.tinyurl.ctrl;

import org.springframework.stereotype.Component;
import service.com.tinyurl.UrlShorteningApiResponse;

@Component
public class UrlShorteningCtrl {


    public UrlShorteningApiResponse run(String originalUrl) {

        // fetch from db for shortened url


        // if not exist, generate one and save in db and return




        return null;
    }

    public class UrlRequest {
        String url;
    }
}
