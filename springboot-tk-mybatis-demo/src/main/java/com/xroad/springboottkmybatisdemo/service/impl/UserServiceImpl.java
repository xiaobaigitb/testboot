package com.xroad.springboottkmybatisdemo.service.impl;

import com.xroad.springboottkmybatisdemo.base.BaseServiceImpl;
import com.xroad.springboottkmybatisdemo.mapper.UserMapper;
import com.xroad.springboottkmybatisdemo.pojo.User;
import com.xroad.springboottkmybatisdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    //UserMapper需要继承tk-myabtis的Mapper,这样才能顺利使用到tk-mybatis帮我们写好的N多mapper层方法和sql语句
    @Autowired
    UserMapper userMapper;


    //仅仅需要编写额外扩展的业务方法
    @Override
    public List<Map> getMyCustom(int age) {
        return userMapper.getMyCustom(age);
    }

}
