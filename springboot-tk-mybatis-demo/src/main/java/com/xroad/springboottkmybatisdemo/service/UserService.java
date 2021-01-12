package com.xroad.springboottkmybatisdemo.service;

import com.xroad.springboottkmybatisdemo.base.BaseService;
import com.xroad.springboottkmybatisdemo.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService extends BaseService<User> {


    //仅仅需要编写额外扩展的业务方法
    List<Map>  getMyCustom(int age);

}
