package com.xroad.springbootmybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xroad.springbootmybatisplusdemo.model.MyInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MyInfoMapper extends BaseMapper<MyInfo> {

    @Select("SELECT * FROM my_info where id=#{id} and name=#{name}")
    MyInfo getMyInfoConditional(@Param("id") Integer id,@Param("name") String name);
}
