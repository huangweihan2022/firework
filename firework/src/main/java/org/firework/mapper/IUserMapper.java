package org.firework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;
import org.firework.entity.User;

import java.util.List;

@Mapper
public interface IUserMapper {

    /**
     * 获取所有用户
     */
    List<User> getAllUser();

    /**
     * 添加用户
     */
    Integer addUser(User user);

    /**
     * 删除用户
     */
    void delUser(Integer uid);

}
