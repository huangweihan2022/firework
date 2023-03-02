package org.firework.controller;

import org.firework.entity.User;
import org.firework.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 获取所有用户
     */
    @GetMapping("/getAll")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    /**
     * 新增用户
     */
    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        this.userService.addUser(user);
        return "ok";
    }

    /**
     * 删除用户
     */
    @PostMapping("/del")
    public String delUser(@RequestBody User user) {
        this.userService.delUser(user.getId());
        return "ok";
    }

    @GetMapping("/get")
    public User getUserById(@RequestBody User user){
        return userService.getUserById(user.getId());
    }

    @GetMapping("/getAllCache")
    public List<User> getAllCache(){
        return userService.getAllCache();
    }
}
