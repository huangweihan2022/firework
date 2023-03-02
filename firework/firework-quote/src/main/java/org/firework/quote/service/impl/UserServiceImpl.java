package org.firework.quote.service.impl;

import org.firework.common.entity.User;
import org.firework.quote.cache.UserCacheFactory;
import org.firework.quote.mapper.IUserMapper;
import org.firework.quote.service.IUserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户-业务层
 */
@Service
public class UserServiceImpl implements IUserService, ApplicationContextAware {

    @Autowired
    private IUserMapper userMapper;

    @Autowired
    private UserCacheFactory userCacheFactory;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取所有用户
     */
    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    /**
     * 新增用户
     */
    @Override
    public void addUser(User user) {
        this.userMapper.addUser(user);
    }

    /**
     * 删除用户
     */
    @Override
    public void delUser(Integer uid) {
        this.userMapper.delUser(uid);
    }

    /**
     * 根据id获取用户信息
     */
    @Override
    public User getUserById(Integer id) {
        return userCacheFactory.get(id);
    }

    /**
     * 获取所有的缓存信息
     */
    @Override
    public List<User> getAllCache() {
        return userCacheFactory.getAllCache();
    }
}
