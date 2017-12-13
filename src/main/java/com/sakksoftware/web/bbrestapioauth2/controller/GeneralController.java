package com.sakksoftware.web.bbrestapioauth2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {

    @RequestMapping("/")
    public String home() {
        return "Home World";
    }

}
