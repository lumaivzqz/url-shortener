package com.lumaivzqz.urlshortener.application.services;

import com.lumaivzqz.urlshortener.application.dtos.UrlDto;
import com.lumaivzqz.urlshortener.domain.entities.Url;
import com.lumaivzqz.urlshortener.infrastructure.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;
    @Value("${default.base-short-url}")
    private String BASE_SHORT_URL;


    public String createShortUrlFrom(final UrlDto urlDto){
        final String longUrl = urlDto.getUrl();

        final Optional<Url> urlOptional = Optional.ofNullable(urlRepository.findByLongUrl(longUrl));

        if(urlOptional.isPresent()) {
            return urlOptional.get().getShortUrl();
        }

        final Url url = urlRepository.save(new Url(longUrl));

        urlRepository.save(url.generateShortUrl(BASE_SHORT_URL));

        return url.getShortUrl();
    }
}
