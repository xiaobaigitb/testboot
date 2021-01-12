package com.xroad.springbootdevtoolsdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @Value(value = "${test-key}")
    private String testKey;


    @GetMapping("testApi")
    public String testHotDeployment() {

        return "Wonderful :"+testKey;
        //return "Wonderful--TEST :"+testKey;


    }

}
