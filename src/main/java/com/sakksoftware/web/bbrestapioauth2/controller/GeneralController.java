package com.sakksoftware.web.bbrestapioauth2.controller;

import lombok.Data;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {

    @GetMapping("/")
    public RestMsg home() {
        return new RestMsg("Hello World!");
    }

    @GetMapping("/api/test")
    public RestMsg apitest() {
        // The authenticated user can be fetched using the SecurityContextHolder
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new RestMsg(String.format("Hello '%s'!", username));
    }

    // A helper class to make our controller output look nice
    @Data
    public static class RestMsg {
        private String msg;

        public RestMsg(String msg) {
            this.msg = msg;
        }

    }

}
