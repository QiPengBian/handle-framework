package com.example.sys.mapper;

import com.example.sys.domain.User;

/**
 * @description: TODO
 * @author: bianqipeng
 * @date: 2021-02-23 10:20:06
 */
public interface UserMapper {

    User selectByPrimaryKey(Long id);
}
