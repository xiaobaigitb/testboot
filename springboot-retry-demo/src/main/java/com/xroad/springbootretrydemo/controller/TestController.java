package com.xroad.springbootretrydemo.controller;

import com.xroad.springbootretrydemo.service.TestRetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @Autowired
    TestRetryService testRetryServiceImpl;

    @GetMapping("/testRetry")
    public String testRetry() throws Exception {
        int code=0;

        int result = testRetryServiceImpl.dignifiedTest(code);
        return "result："+result;
    }

}
