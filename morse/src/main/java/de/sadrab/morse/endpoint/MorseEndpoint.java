package de.sadrab.morse.endpoint;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/morse")
public class MorseEndpoint {
    private final ResourceLoader resourceLoader;

    public MorseEndpoint(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @PostMapping
    String get(@RequestBody String letter) {
        return morseCode().get(letter);
    }

    private Map<String, String> morseCode() {
        Map<String, String> map = new HashMap<>();
        Resource resource = resourceLoader.getResource("classpath:morseCode.txt");
        try {
            Files.lines(resource.getFile().toPath())
                    .map(line -> line.split(","))
                    .forEach(e -> map.put(e[0], e[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
