package com.example.springboot.controller;

import com.example.springboot.entity.SpringProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class homeController {

    @Autowired
    SpringProperties springProperties;

    @RequestMapping(value = "/")
    public ResponseEntity helloWorld(HttpServletRequest request) {
        request.getRemotePort();
        System.out.println(request.getRemotePort());
        return new ResponseEntity("Hello World!", HttpStatus.OK);
    }

    @RequestMapping(value = "/getInfoProperties")
    public ResponseEntity getInfo() {
        String name = springProperties.getApplication().getName().toString();
        System.out.println(name);
        return new ResponseEntity(springProperties, HttpStatus.OK);
    }
}
