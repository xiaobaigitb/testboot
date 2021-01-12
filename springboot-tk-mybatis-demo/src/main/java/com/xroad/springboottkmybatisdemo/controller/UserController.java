package com.xroad.springboottkmybatisdemo.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xroad.springboottkmybatisdemo.pojo.User;
import com.xroad.springboottkmybatisdemo.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 新增
     * @return
     */
    @PostMapping("addOne")
    public int addOne() {
        User user=new User();
        user.setName("jc");
        user.setAge(10);
        return userService.insert(user);
    }

    /**
     * 获取所有 分页
     * @return
     */
    @GetMapping("getAllByPage")
    public List<User> getAllByPage() {
        RowBounds rowBounds=new RowBounds(0,3);
        return userService.selectByRowBounds(new User(),rowBounds);
    }

    /**
     * 获取某个
     * @return
     */
    @GetMapping("getOne")
    public User getOne() {
        User user=new User();
        user.setId(1);
        return userService.selectOne(user);
    }

    /**
     * 获取所有
     * @return
     */
    @GetMapping("getAll")
    public List<User> getAll() {

        return userService.selectAll();
    }

    /**
     * 获取所有 分页 PageHelper
     * @return
     */
    @GetMapping("getAllByPage")
    public PageInfo getAllPage() {
        PageHelper.startPage(1, 2);
        PageInfo pageInfo = new PageInfo(userService.selectAll());
        return pageInfo;
    }

    /**
     * 修改
     * @return
     */
    @GetMapping("updateOne")
    public int updateOne() {
        User user=new User();
        user.setId(1);
        user.setName("TG");
        return userService.updateByPrimaryKeySelective(user);
    }


    /**
     * 额外扩展的
     * @return
     */
    @GetMapping("getMyCustom")
    public List<Map> getMyCustom() {
        return userService.getMyCustom(1);
    }

}
