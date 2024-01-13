package com.lumaivzqz.urlshortener.application.services;

import com.lumaivzqz.urlshortener.domain.entities.Url;
import com.lumaivzqz.urlshortener.infrastructure.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    private static final String BASE_SHORT_URL = "http://www.shorturl.com/";

    public String createShortUrlFrom(String longUrl){
        Optional<Url> urlOptional = Optional.ofNullable(urlRepository.findByLongUrl(longUrl));

        if(urlOptional.isPresent()) {
            return urlOptional.get().getShortUrl();
        }

        Url url = new Url(longUrl).generateShortUrl(BASE_SHORT_URL);

        urlRepository.save(url);

        return url.getShortUrl();
    }
}
