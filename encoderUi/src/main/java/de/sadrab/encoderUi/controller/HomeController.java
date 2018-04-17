package de.sadrab.encoderUi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    private MorceEncoder morceEncoder;

    private String text1;

    public HomeController(MorceEncoder morceEncoder) {
        this.morceEncoder = morceEncoder;
    }

    @ModelAttribute("result")
    String result() {
        return morceEncoder.encode(text1);
    }
    @ModelAttribute("text")
    Text text() {
        return new Text();
    }

    @PostMapping
    String post(Text text) {
        this.text1=text.getText();
        return "redirect:/";
    }

    @GetMapping
    String page() {
        return "home";
    }

}
