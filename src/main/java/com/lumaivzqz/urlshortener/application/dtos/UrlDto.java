package com.lumaivzqz.urlshortener.application.dtos;

public class UrlDto {
    private String url;

    public UrlDto() {
    }

    public UrlDto(final String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }
}
