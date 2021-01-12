package com.xroad.testboot.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;
import java.sql.*;


@Component
public class ExecuteSQLUtil {

    @Value("${spring.datasource.druid.url}")
    private String DB_URL;
    @Value("${spring.datasource.druid.username}")
    private String DB_USERNAME;
    @Value("${spring.datasource.druid.password}")
    private String DB_PWD;

    public  Connection executeSql(String sqlFileName){
        Connection connection = null;
        try {
            String driverClassName = "com.mysql.cj.jdbc.Driver";
//            String DB_URL = "jdbc:mysql://127.0.0.1:3306/test?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8&useSSL=true&rewriteBatchedStatements=true";
//            String DB_USERNAME = "root";
//            String DB_PWD = "123456";

            Class.forName(driverClassName);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PWD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ClassPathResource rc = new ClassPathResource(sqlFileName);
        EncodedResource er = new EncodedResource(rc, "utf-8");
        ScriptUtils.executeSqlScript(connection, er);
        return connection;
    }


}
