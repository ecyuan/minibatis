package com.eyuan.minibatis.mapper;

import com.eyuan.minibatis.annotation.Select;

/**
 * 用户数据表映射的Mapper
 */
public interface UserMapper {
    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    @Select("select * from user where id = #")
    String getOne(Integer id);
}
