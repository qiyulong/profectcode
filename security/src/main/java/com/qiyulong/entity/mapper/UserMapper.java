package com.qiyulong.entity.mapper;

import com.qiyulong.entity.Role;
import com.qiyulong.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User loadUserByUsername(String username);

    List<Role> getUserRolesById(Integer userid);
}
