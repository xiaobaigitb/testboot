package com.xroad.testboot.controller;

import com.xroad.testboot.utils.ExecuteSQLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SqlController {
    @Autowired
    ExecuteSQLUtil executeSQLUtil;

    @GetMapping("/executeMysql")
    public String executeMysql(@RequestParam("scriptName") String scriptName) {
        executeSQLUtil.executeSql("static/sql/"+scriptName+".sql");
        return "ok";
    }
}
