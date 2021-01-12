package com.xroad.springbootshirodemo.mapper;

import com.xroad.springbootshirodemo.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface LoginMapper {

    SysUser queryUser(String userName );

    List<Map<String,Object>> getUserPower(String userName);
}