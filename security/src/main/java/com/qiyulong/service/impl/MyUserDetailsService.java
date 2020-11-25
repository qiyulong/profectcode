package com.qiyulong.service.impl;

import com.qiyulong.entity.User;
import com.qiyulong.entity.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Resource
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登录用户名:" + username);
        User user = userMapper.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在!");
        }
        user.setRoles(userMapper.getUserRolesById(user.getId()));
        return user;
//        return new User(username,passwordEncoder.encode("123456")
//                ,true
//                ,true
//                ,true
//                ,true
//                , AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
