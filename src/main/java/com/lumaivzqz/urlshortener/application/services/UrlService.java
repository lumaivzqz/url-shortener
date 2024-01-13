package com.lumaivzqz.urlshortener.application.services;

import com.lumaivzqz.urlshortener.domain.entities.Url;
import com.lumaivzqz.urlshortener.domain.services.CreateShortUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    @Autowired
    private CreateShortUrl createShortUrl;


    public String createShortUrlFrom(String longUrl){
        // check if it was already saved into the database
        // getShortUrlUsecase.execute(longUrl);
        // if it is, returns short url, if not:

        Url url = createShortUrl.execute(new Url(longUrl));

        // save it

        return url.getShortUrl();
    }
}
