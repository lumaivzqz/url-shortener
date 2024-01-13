package com.lumaivzqz.urlshortener.application.controllers;

import com.lumaivzqz.urlshortener.application.dtos.UrlDto;
import com.lumaivzqz.urlshortener.application.reponses.ShortUrlResponse;
import com.lumaivzqz.urlshortener.application.services.UrlService;
import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UrlControllerTest {
    @Mock
    private UrlService urlService;

    @InjectMocks
    private UrlController urlController;

    @Test
    @DisplayName("Should creates short url when it receives nonnull body parameter")
    void testCreateShortUrl() {
        String longUrl = "https://www.urlshortener.com/q=someinfomation&s=moreinformation&&v=v5&&g=extrainformation";
        String shortUrl = "https://www.urlshortener.com/2u3ui2==";

        ShortUrlResponse expected = new ShortUrlResponse(shortUrl);

        // when
        when(urlService.createShortUrlFrom(any())).thenReturn(shortUrl);

        ResponseEntity actual = urlController.createShortUrlFrom(new UrlDto(longUrl));

        // then
        assertEquals(expected.toString(), actual.getBody().toString());
    }
}
