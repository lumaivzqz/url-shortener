package com.lumaivzqz.urlshortener.domain.services;

import com.lumaivzqz.urlshortener.domain.entities.Url;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CreateShortUrlTest {

    @Autowired
    private CreateShortUrl createShortUrl;

    @Test
    @DisplayName("Should works successfully")
    void testExecute() {
        // given
        Url longUrl = new Url("https://www.urlshortener.com/q=someinfomation&s=moreinformation&&v=v5&&g=extrainformation");
        Url expected = longUrl;

        // when
        Url actual = createShortUrl.execute(longUrl);

        // then
        assertEquals(expected, actual);
    }

}