package com.lumaivzqz.urlshortener.domain.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UrlTest {
    private  static final  String LONG_URL = "https://www.urlshortener.com/q=someinfomation&s=moreinformation&&v=v5&&g=extrainformation";
    private static final   String SHORT_URL = "http://www.shorturl.com/bnVsbA==";
    private static final   String BASE_URL = "http://www.shorturl.com/";

    @Test
    @DisplayName("Should generate short url successfully")
    void testGenerateShortUrl() {
        // given
        // when
        Url actual = new Url(LONG_URL).generateShortUrl(BASE_URL);

        // then
        assertEquals(SHORT_URL, actual.getShortUrl());
    }

}