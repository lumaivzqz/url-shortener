package com.lumaivzqz.urlshortener.application.controllers;

import com.lumaivzqz.urlshortener.application.dtos.UrlDto;
import com.lumaivzqz.urlshortener.application.reponses.ShortUrlResponse;
import com.lumaivzqz.urlshortener.application.services.UrlService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UrlControllerTest {

    static final String LONG_URL = "https://www.urlshortener.com/q=someinfomation&s=moreinformation&&v=v5&&g=extrainformation";
    static final String SHORT_URL = "https://www.urlshortener.com/2u3ui2==";

    @Mock
    private UrlService urlService;

    @InjectMocks
    private UrlController urlController;

    @Test
    @DisplayName("Should creates short url when it receives nonnull body parameter")
    void testCreateShortUrl() {
        // given
        ShortUrlResponse expected = new ShortUrlResponse(SHORT_URL);

        // when
        when(urlService.createShortUrlFrom(any())).thenReturn(SHORT_URL);

        ResponseEntity actual = urlController.createShortUrlFrom(new UrlDto(LONG_URL));

        // then
        assertEquals(expected.toString(), actual.getBody().toString());
    }

    @Test
    @DisplayName("Should get long url when it already exists")
    void testGetLongUrl() {
        // given
        // when
        try {
            when(urlService.getLongUrlFrom(any())).thenReturn(new URI(LONG_URL));
        } catch (Exception e ){
            fail();
        }

        ResponseEntity actual = urlController.getLongUrlFrom(SHORT_URL);

        // then
        assertEquals(HttpStatus.PERMANENT_REDIRECT, actual.getStatusCode());
        assertEquals(LONG_URL, actual.getHeaders().getLocation().toString());
    }
}
