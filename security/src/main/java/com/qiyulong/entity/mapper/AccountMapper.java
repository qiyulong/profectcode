package com.qiyulong.entity.mapper;

import com.qiyulong.entity.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    Account findById(Integer id);
}
