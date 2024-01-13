package com.lumaivzqz.urlshortener.application.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.lumaivzqz.urlshortener.domain.entities.Url;
import com.lumaivzqz.urlshortener.domain.services.CreateShortUrl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UrlServiceTest {

    @InjectMocks
    private UrlService urlService;

    @Mock
    private CreateShortUrl createShortUrl;

    @Test
    @DisplayName("Should works successfully")
    void testCreateShortUrlFrom() {
        // given
        String longUrl = "https://www.urlshortener.com/q=someinfomation&s=moreinformation&&v=v5&&g=extrainformation";
        String shortUrl = "http://www.shorturl.com/1uy234i";

        Url expected = new Url(longUrl);
        expected.setShortUrl(shortUrl);

        // when
        when(createShortUrl.execute(any())).thenReturn(expected);

        String actual = urlService.createShortUrlFrom(longUrl);

        // then
        assertEquals(expected.getShortUrl(), actual);
    }

}