package com.lumaivzqz.urlshortener.application.services;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Base64;

@Service
public class Base64Service {


    private static final Base64.Decoder decoder = Base64.getDecoder();

    private static final Base64.Encoder encoder = Base64.getEncoder();

    public Long decode(final String value) throws UnsupportedEncodingException {
        return Long.decode(new String(decoder.decode(value), "UTF-8"));
    }

    public String encode(final Long value) {
        return encoder.encodeToString(String.valueOf(value).getBytes());
    }
}
