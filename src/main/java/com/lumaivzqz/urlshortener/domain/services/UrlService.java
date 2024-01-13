package com.lumaivzqz.urlshortener.domain.services;

import com.lumaivzqz.urlshortener.domain.usecases.CreateShortUrlUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    @Autowired
    private CreateShortUrlUsecase createShortUrlUsecase;

    public String createShortUrlFrom(String longUrl){
        return createShortUrlUsecase.execute(longUrl);
    }
}
