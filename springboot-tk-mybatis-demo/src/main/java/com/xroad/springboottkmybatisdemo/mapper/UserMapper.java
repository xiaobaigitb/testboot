package com.xroad.springboottkmybatisdemo.mapper;

import com.xroad.springboottkmybatisdemo.pojo.User;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;
import java.util.Map;

public interface UserMapper extends Mapper<User> {

    //额外扩展的方法，如一些复杂的查询语句等
    List<Map> getMyCustom(int age);
}
