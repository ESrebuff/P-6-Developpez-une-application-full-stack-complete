package com.openclassrooms.mddapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/example")
@RequiredArgsConstructor
public class ExampleController {
    @PostMapping
    public String welcome()
    {
        return "Welcome from secure endpoint";
    }
}