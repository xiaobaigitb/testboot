package com.xroad.testboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author : Xroad
 * @CreateTime : 2021/1/6
 * @Description :
 **/
@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/testPage")
    public String testPage() {
        System.out.println("方法已经访问到了！");
        return "/JCccc/testPage";
    }
}
