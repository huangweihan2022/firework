package org.firework.quote.service;

import org.firework.common.entity.User;

import java.util.List;

public interface IUserService {

    /**
     * 获取所有用户
     */
    List<User> getAllUser();

    /**
     * 添加用户
     */
    void addUser(User user);

    /**
     * 删除用户
     */
    void delUser(Integer uid);

    /**
     * 根据id获取用户信息
     */
    User getUserById(Integer id);

    /**
     * 获取所有缓存
     */
    List<User> getAllCache();
}
