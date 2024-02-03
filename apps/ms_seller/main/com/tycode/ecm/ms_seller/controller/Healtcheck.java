package com.tycode.ecm.ms_seller.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Healtcheck {

    @GetMapping(value = "/healtcheck")
    public String healtcheck(){
        return "seller:ok";
    }
}
