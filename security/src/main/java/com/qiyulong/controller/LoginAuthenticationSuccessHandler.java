package com.qiyulong.controller;

import com.alibaba.fastjson.JSON;
import com.qiyulong.config.LoginType;
import com.qiyulong.entity.SecurityPropertities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component("loginAuthenticationSuccessHandler")
//public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
public class LoginAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    SecurityPropertities securityPropertities;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功!!!");
        if (LoginType.JSON.equals(securityPropertities.getLoginType())){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(authentication));
        }else{
            super.onAuthenticationSuccess(request,response,authentication);
        }
    }
}
