package com.qiyulong.service.impl;

import com.qiyulong.entity.Account;
import com.qiyulong.entity.mapper.AccountMapper;
import com.qiyulong.service.IAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements IAccountService {

    @Resource
    AccountMapper accountMapper;

    @Override
    public Account findById(Integer id) {
        return accountMapper.findById(id);
    }
}
