package com.lumaivzqz.urlshortener.application.services;

import com.lumaivzqz.urlshortener.domain.entities.Url;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private static final String BASE_SHORT_URL = "http://www.shorturl.com/";

    public String createShortUrlFrom(String longUrl){
        // check if it was already saved into the database
        // getShortUrlUsecase.execute(longUrl);
        // if it is, returns short url, if not:

        Url url = new Url(longUrl).generateShortUrl(BASE_SHORT_URL);

        // save it

        return url.getShortUrl();
    }
}
