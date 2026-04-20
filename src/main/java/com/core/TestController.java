package com.core;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/info")
    public String info() {
        return "Spring Boot is running!";
    }
}
