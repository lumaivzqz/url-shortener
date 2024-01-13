package com.lumaivzqz.urlshortener.domain.services;

import com.lumaivzqz.urlshortener.domain.entities.Url;
import org.springframework.stereotype.Service;

@Service
public class CreateShortUrl {

    public Url execute(Url url) {

        return url;
    };
}
