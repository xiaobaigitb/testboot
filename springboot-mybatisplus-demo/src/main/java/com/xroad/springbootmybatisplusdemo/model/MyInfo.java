package com.xroad.springbootmybatisplusdemo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class MyInfo {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer num;
    private String name;
    private  Integer age;
}