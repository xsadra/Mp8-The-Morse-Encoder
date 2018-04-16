package de.sadrab.encoder.endpoint;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/encode")
public class encoderEndPoint {

    private RestTemplate restTemplate;

    @Value("${morse.url}")
    private String morseUrl;

    public encoderEndPoint(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping
    String getMessage(@RequestBody String message) {


        java.awt.Toolkit.getDefaultToolkit().beep();
        System.out.flush();

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {        }


        return Stream.of(message.split(""))
                .filter(letter -> {
                    char c = letter.charAt(0);
                    return (Character.isLetter(c) || Character.isDigit(c) || Character.isSpaceChar(c));
                })
                .map(letter -> restTemplate.postForObject(morseUrl, letter.toUpperCase(), String.class))
                .collect(Collectors.joining("  "));
    }
}
