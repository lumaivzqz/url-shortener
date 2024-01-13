package com.lumaivzqz.urlshortener.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Base64;

@Entity
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String longUrl;
    private String shortUrl;

    public Url(String longUrl) {
        this.longUrl = longUrl;
    }

    public void setShortUrl(String url) {
        this.shortUrl = url;
    }

    public String getShortUrl(){
        return this.shortUrl;
    }

    public Url generateShortUrl(String baseUrl) {
        String shortUrl = Base64.getEncoder().encodeToString(String.valueOf(this.id).getBytes());

        this.setShortUrl(baseUrl + shortUrl);

        return this;
    }
}
