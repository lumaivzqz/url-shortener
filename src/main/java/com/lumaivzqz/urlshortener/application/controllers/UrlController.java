package com.lumaivzqz.urlshortener.application.controllers;

import com.lumaivzqz.urlshortener.application.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/v1/url")
    public String createShortUrlFrom(@RequestBody @NonNull String longUrl) {
        return urlService.createShortUrlFrom(longUrl);
    }
}