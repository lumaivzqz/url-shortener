package com.lumaivzqz.urlshortener.application.presenters;

import com.lumaivzqz.urlshortener.application.reponses.ShortUrlResponse;

public class ShortUrlPresenter {
    private String shortUrl;

    public ShortUrlPresenter(){}

    public  ShortUrlPresenter(final String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public ShortUrlResponse present() {
        return new ShortUrlResponse(this.shortUrl);
    }

}
