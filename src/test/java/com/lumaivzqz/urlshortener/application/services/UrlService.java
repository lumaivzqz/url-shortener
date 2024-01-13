package com.lumaivzqz.urlshortener.application.services;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.lumaivzqz.urlshortener.domain.entities.Url;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UrlServiceTest {

    @InjectMocks
    private UrlService urlService;

    @Test
    @DisplayName("Should works successfully")
    void testCreateShortUrlFrom() {
        // given
        String longUrl = "https://www.urlshortener.com/q=someinfomation&s=moreinformation&&v=v5&&g=extrainformation";
        String shortUrl = "http://www.shorturl.com/bnVsbA==";

        Url expected = new Url(longUrl);
        expected.setShortUrl(shortUrl);

        // when
        String actual = urlService.createShortUrlFrom(longUrl);

        // then
        assertEquals(expected.getShortUrl(), actual);
    }

}