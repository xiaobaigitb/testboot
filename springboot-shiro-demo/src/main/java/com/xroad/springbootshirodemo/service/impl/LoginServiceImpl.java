package com.xroad.springbootshirodemo.service.impl;

import com.xroad.springbootshirodemo.mapper.LoginMapper;
import com.xroad.springbootshirodemo.pojo.SysUser;
import com.xroad.springbootshirodemo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginMapper loginMapper;
    @Override
    public SysUser queryUser(String userName) {
        return loginMapper.queryUser(userName);
    }

    @Override
    public List<Map<String, Object>> getUserPower(String userName) {
        return loginMapper.getUserPower(userName);
    }
}