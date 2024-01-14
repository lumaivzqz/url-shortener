package com.lumaivzqz.urlshortener.application.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.lumaivzqz.urlshortener.application.dtos.UrlDto;
import com.lumaivzqz.urlshortener.domain.entities.Url;
import com.lumaivzqz.urlshortener.infrastructure.repositories.UrlRepository;
import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UrlServiceTest {

    final static  String longUrl = "https://www.urlshortener.com/q=someinfomation&s=moreinformation&&v=v2&&g=extrainformation";
    final static String shortUrl = "/api/v1/bnVsbA==";

    @InjectMocks
    private UrlService urlService;

    @Mock
    private UrlRepository urlRepository;

    @Test
    @DisplayName("Should works successfully")
    void testCreateShortUrlFrom() {
        // given
        Url url = new Url(longUrl);
        url.setShortUrl(shortUrl);

        // when
        when(urlRepository.findByLongUrl(any())).thenReturn(null);
        when(urlRepository.save(any())).thenReturn(url);

        String actual = urlService.createShortUrlFrom(new UrlDto(longUrl));

        // then
        assertEquals(shortUrl, actual);
    }

    @Test
    @DisplayName("Should returns short url when url already exists")
    void testLongUrlAlreadyExists() {
        // given
        Url expected = new Url(longUrl);
        expected.setShortUrl(shortUrl);

        // when
        when(urlRepository.findByLongUrl(longUrl)).thenReturn(expected);

        String actual = urlService.createShortUrlFrom(new UrlDto(longUrl));

        // then
        verify(urlRepository, never()).save(any());
        assertEquals(expected.getShortUrl(), actual);
    }

    @Test
    @DisplayName("Should failed when it tries to save")
    void testCreateShortUrlFromFailedSaving() {
        // given
        Url expected = new Url(longUrl);
        expected.setShortUrl(shortUrl);

        // when
        when(urlRepository.findByLongUrl(any())).thenReturn(null);
        when(urlRepository.save(any())).thenThrow(new EntityExistsException());

        // then
        assertThrows(EntityExistsException.class, () -> {
            urlService.createShortUrlFrom(new UrlDto(longUrl));
        });
    }

}