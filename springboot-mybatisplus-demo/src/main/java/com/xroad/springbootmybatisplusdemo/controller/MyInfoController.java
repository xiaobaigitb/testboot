package com.xroad.springbootmybatisplusdemo.controller;

import com.xroad.springbootmybatisplusdemo.mapper.MyInfoMapper;
import com.xroad.springbootmybatisplusdemo.model.MyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @Author : JCccc
 * @CreateTime : 2019/12/4
 * @Description :
 **/
@RestController
public class MyInfoController {
    @Autowired
    MyInfoMapper myInfoMapper;

    @GetMapping("/testPlus")
    public String testPlus() {
        MyInfo myInfo1 = new MyInfo();

        myInfo1.setNum(1011909);
        myInfo1.setName("one");
        myInfo1.setAge(11);
        int effectNum1 = myInfoMapper.insert(myInfo1);
        System.out.println("添加后的影响行数："+effectNum1+"--- 1主键："+myInfo1.getId());
        MyInfo myInfo2=new MyInfo();

        myInfo2.setNum(1011220);
        myInfo2.setName("two");
        myInfo2.setAge(30);
        int effectNum2 = myInfoMapper.insert(myInfo2);
        System.out.println("添加后的影响行数："+effectNum2+"--- 2主键："+myInfo2.getId());

        List<MyInfo> myInfoList = myInfoMapper.selectList(null);
        System.out.println("查询出来的list："+myInfoList.toString());

        // int effectNumeDelete = myInfoMapper.deleteById(2);

        // System.out.println("删除后影响行数："+effectNumeDelete);

        MyInfo myInfoConditional = myInfoMapper.getMyInfoConditional(2, "two");

        System.out.println("根据条件查询出来的数据："+myInfoConditional.toString());

        return myInfoConditional.toString();
    }
}
