package com.lumaivzqz.urlshortener.application.services;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.lumaivzqz.urlshortener.application.dtos.UrlDto;
import com.lumaivzqz.urlshortener.domain.entities.Url;
import com.lumaivzqz.urlshortener.infrastructure.repositories.UrlRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.InputMismatchException;
import java.util.Optional;

@SpringBootTest
class UrlServiceTest {

    final static  String longUrl = "https://www.urlshortener.com/q=someinfomation&s=moreinformation&&v=v2&&g=extrainformation";
    final static String shortUrl = "/api/v1/bnVsbA==";

    @InjectMocks
    private UrlService urlService;

    @Mock
    private UrlRepository urlRepository;

    @Mock
    private Base64Service base64Service;

    @Test
    @DisplayName("Create short url should works successfully")
    void testCreateShortUrlFrom() {
        // given
        Url url = new Url(longUrl);
        url.setShortUrl(shortUrl);

        // when
        when(urlRepository.findByLongUrl(any())).thenReturn(null);
        when(urlRepository.save(any())).thenReturn(url);
        when(base64Service.encode(any())).thenReturn("bnVsbA==");

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

    @Test
    @DisplayName("Get long url should works successfully")
    void testGetLongUrlFrom() {
        // given
        Url url = new Url(longUrl);
        url.setShortUrl(shortUrl);

        final Long id = 1l;

        // when
        try {
            when(base64Service.decode(any())).thenReturn(id);
        } catch (Exception e) {
            fail();
        }
        when(urlRepository.findById(any())).thenReturn(Optional.of(url));

        final URI actual = urlService.getLongUrlFrom(shortUrl);

        // then
        assertEquals(longUrl, actual.toString());
    }

    @Test
    @DisplayName("Should throw unsupported encoding exception when short url was not right encoded")
    void testShortUrlNotRightEncoded() {
        // given
        Url url = new Url(longUrl);
        url.setShortUrl(shortUrl);

        // when
        try {
            when(base64Service.decode(any())).thenThrow(new UnsupportedEncodingException());
        } catch (Exception e) {
            fail();
        }

        // then
        assertThrows(InputMismatchException.class, () -> {
            urlService.getLongUrlFrom(shortUrl);
        });

        verify(urlRepository, never()).findById(any());
    }

    @Test
    @DisplayName("Should throw entity do not found exception when short url does not exist")
    void testShortUrlDoesNotExist() {
        // given
        Url url = new Url(longUrl);
        url.setShortUrl(shortUrl);

        final Long id = 1l;

        // when
        try {
        when(base64Service.decode(any())).thenReturn(id);
        } catch (Exception e) {
            fail();
        }
        when(urlRepository.findById(any())).thenThrow(new EntityNotFoundException("URL not found"));

        // then
        assertThrows(EntityNotFoundException.class, () -> {
            urlService.getLongUrlFrom(shortUrl);
        });
    }
}