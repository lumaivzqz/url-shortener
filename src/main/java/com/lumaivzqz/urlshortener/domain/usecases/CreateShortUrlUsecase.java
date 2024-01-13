package com.lumaivzqz.urlshortener.domain.usecases;

import org.springframework.stereotype.Service;

@Service
public class CreateShortUrlUsecase {

    public String execute(String longUrl) {
      return longUrl;
    };
}
