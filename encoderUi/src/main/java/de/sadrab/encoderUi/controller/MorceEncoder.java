package de.sadrab.encoderUi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MorceEncoder {
    private RestTemplate rest;

    @Value("${encoder.url}")
    private String encoderUrl;

    public MorceEncoder(RestTemplate rest) {
        this.rest = rest;
    }

    public String encode(String text) {
        if (text == null)
            return "";
        return rest.postForObject(encoderUrl,text,String.class);
    }
}
