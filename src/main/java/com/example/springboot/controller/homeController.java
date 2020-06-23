package com.example.springboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController {

    @RequestMapping(value = "/")
    public ResponseEntity helloWorld() {
        return new ResponseEntity("Hello World!", HttpStatus.OK);
    }
}
