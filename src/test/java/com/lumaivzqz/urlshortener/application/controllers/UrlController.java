package com.lumaivzqz.urlshortener.application.controllers;

import com.lumaivzqz.urlshortener.application.services.UrlService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class UrlControllerTest {
    @InjectMocks
    private UrlController urlController;

    @Mock
    private UrlService urlService;

    @Test
    @DisplayName("Should creates short url when it receives nonnull body parameter")
    void testCreateShortUrl() {
        String longUrl = "https://www.urlshortener.com/q=someinfomation&s=moreinformation&&v=v5&&g=extrainformation";
        String expected = "https://www.urlshortener.com/q=someinfomation&s=moreinformation&&v=v5&&g=extrainformation";

        // when
        when(urlService.createShortUrlFrom(longUrl)).thenReturn(expected);

        String actual = urlController.createShortUrlFrom(longUrl);

        // then
        assertEquals(expected, actual);
    }

}
