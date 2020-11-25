package com.qiyulong.controller;

import com.qiyulong.entity.Account;
import com.qiyulong.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    IAccountService accountService;

    @GetMapping("fbi")
    Account findById(@RequestParam(required = true) Integer id){
        return accountService.findById(id);
    }
}
