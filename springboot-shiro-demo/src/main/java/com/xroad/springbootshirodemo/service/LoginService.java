package com.xroad.springbootshirodemo.service;

import com.xroad.springbootshirodemo.pojo.SysUser;

import java.util.List;
import java.util.Map;

public interface LoginService {

    SysUser queryUser(String  userName );

    List<Map<String,Object>> getUserPower(String  userName );
}
