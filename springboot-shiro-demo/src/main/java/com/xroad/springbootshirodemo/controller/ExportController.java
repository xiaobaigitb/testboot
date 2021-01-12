package com.xroad.springbootshirodemo.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExportController {
    @RequiresRoles("admin")
    @RequiresPermissions("exportUserInfo")
    @ResponseBody
    @RequestMapping("/export")
    public String export() {
        return "u  can export !";
    }

}
