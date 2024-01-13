package com.lumaivzqz.urlshortener.application.controllers;

import com.lumaivzqz.urlshortener.application.dtos.UrlDto;
import com.lumaivzqz.urlshortener.application.presenters.ShortUrlPresenter;
import com.lumaivzqz.urlshortener.application.reponses.ShortUrlResponse;
import com.lumaivzqz.urlshortener.application.services.UrlService;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/v1/url")
    public ResponseEntity<Object> createShortUrlFrom(@RequestBody @NonNull final UrlDto urlDto) {
        try {
            final ShortUrlPresenter presenter = new ShortUrlPresenter(urlService.createShortUrlFrom(urlDto));

            return ResponseEntity.status(HttpStatus.CREATED).body(presenter.present());
        } catch (EntityExistsException e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}