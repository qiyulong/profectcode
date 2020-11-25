package com.qiyulong.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Account implements Serializable {
    Integer id;
    String name;
    String pwd;
    String perms;
}
