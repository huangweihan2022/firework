package org.firework.entity;

import lombok.Data;

/**
 * 用户实体类
 */
@Data
public class User {
    /**
     * 用户主键
     */
    private Integer id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 用户年龄
     */
    private Integer age;

    /**
     * 用户地址
     */
    private String address;
}
