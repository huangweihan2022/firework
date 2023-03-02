package org.firework.quote.cache;

import org.firework.common.entity.User;
import org.firework.quote.mapper.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

/**
 * 用户缓存
 */
@Component
public class UserCacheFactory {

    private static final CacheManager<Integer, User> USER_CACHE_MAP = new CacheManager<>(new HashMap<>());

    @Autowired
    private IUserMapper userMapper;

    @PostConstruct
    public void init(){
        List<User> allUser = userMapper.getAllUser();
        allUser.forEach(p -> USER_CACHE_MAP.put(p.getId(), p));
    }

    public void add(Integer key, User user){
        USER_CACHE_MAP.put(key, user);
    }

    public void remove(Integer key){
        USER_CACHE_MAP.remove(key);
    }

    public User get(Integer id) {
        return USER_CACHE_MAP.get(id);
    }

    public List<User> getAllCache(){
        return USER_CACHE_MAP.getAllCache();
    }
}
