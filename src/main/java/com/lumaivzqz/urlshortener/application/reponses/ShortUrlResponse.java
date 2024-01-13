package com.lumaivzqz.urlshortener.application.reponses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShortUrlResponse {
    @JsonProperty("url")
    public String url;

    public ShortUrlResponse() {
    }

    public ShortUrlResponse(final String shorUrl) {
        this.url = shorUrl;
    }

    @Override
    public String toString() {
        return "ShortUrlResponse{" +
                "url='" + url + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
