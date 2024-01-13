package com.lumaivzqz.urlshortener.domain.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.lumaivzqz.urlshortener.domain.usecases.CreateShortUrlUsecase;
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
    private CreateShortUrlUsecase createShortUrlUsecase;

    @Test
    @DisplayName("Should works successfully")
    void testCreateShortUrlFrom() {
        // given
        String longUrl = "https://www.urlshortener.com/q=someinfomation&s=moreinformation&&v=v5&&g=extrainformation";
        String expected = "https://www.urlshortener.com/q=someinfomation&s=moreinformation&&v=v5&&g=extrainformation";

        // when
        when(createShortUrlUsecase.execute(longUrl)).thenReturn(expected);

        String actual = urlService.createShortUrlFrom(longUrl);

        // then
        assertEquals(expected, actual);
    }

}