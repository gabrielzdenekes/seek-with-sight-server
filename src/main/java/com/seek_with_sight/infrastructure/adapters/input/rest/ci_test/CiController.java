package com.seek_with_sight.infrastructure.adapters.input.rest.ci_test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ci")
public class CiController {
    @GetMapping
    public String get() {
        return "CI";
    }
}
