package com.xroad.springbootshirodemo.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
    //注解验角色和权限
    @RequiresRoles("common")
    @ResponseBody
    @RequestMapping("/querySystemLog")
    public String queryLog() {
        return "u  can queryLog !";
    }



}
