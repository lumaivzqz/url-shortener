package com.lumaivzqz.urlshortener.domain.usecases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CreateShortUrlTest {

    @Autowired
    private CreateShortUrlUsecase createShortUrlUsecase;

    @Test
    @DisplayName("Should works successfully")
    void testExecute() {
        // given
        String longUrl = "https://www.urlshortener.com/q=someinfomation&s=moreinformation&&v=v5&&g=extrainformation";
        String expected = "https://www.urlshortener.com/q=someinfomation&s=moreinformation&&v=v5&&g=extrainformation";

        // when
        String actual = createShortUrlUsecase.execute(longUrl);

        // then
        assertEquals(expected, actual);
    }

}