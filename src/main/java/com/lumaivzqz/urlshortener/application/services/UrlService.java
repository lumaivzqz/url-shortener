package com.lumaivzqz.urlshortener.application.services;

import com.lumaivzqz.urlshortener.application.dtos.UrlDto;
import com.lumaivzqz.urlshortener.domain.entities.Url;
import com.lumaivzqz.urlshortener.infrastructure.repositories.UrlRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.InputMismatchException;
import java.util.Optional;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private Base64Service base64Service;

    private static final String PREFIX_URL = "/api/v1/";

    public String createShortUrlFrom(final UrlDto urlDto){
        final String longUrl = urlDto.getUrl();

        final Optional<Url> urlOptional = Optional.ofNullable(urlRepository.findByLongUrl(longUrl));

        if(urlOptional.isPresent()) {
            return urlOptional.get().getShortUrl();
        }

        final Url url = urlRepository.save(new Url(longUrl));

        urlRepository.save(url.generateShortUrl(PREFIX_URL));

        return url.getShortUrl();
    }

    public URI getLongUrlFrom(final String shortUrl) {
        Long id;

        try {
            id = base64Service.decode(shortUrl);
        } catch(NumberFormatException e) {
            throw new InputMismatchException("URL is not valid");
        }

        final Optional<Url> url = urlRepository.findById(id);
        if(url.isEmpty()) {
            throw new EntityNotFoundException("URL not found");
        }

        return url.get().getURI();
    }
}
