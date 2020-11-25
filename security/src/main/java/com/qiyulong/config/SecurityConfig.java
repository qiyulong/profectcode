package com.qiyulong.config;

import com.qiyulong.controller.LoginAuthenticationFailureHandler;
import com.qiyulong.controller.LoginAuthenticationSuccessHandler;
import com.qiyulong.entity.SecurityPropertities;
import com.qiyulong.service.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(SecurityPropertities.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SecurityPropertities securityPropertities;

    @Autowired
    LoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler;

    @Autowired
    LoginAuthenticationFailureHandler loginAuthenticationFailureHandler;

    @Autowired
    MyUserDetailsService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() //允许表单登录
                .loginPage("/authencation/require") //设置表单登录页
                //使用/authentication/form的url来处理表单登录请求
                .loginProcessingUrl("/authentication/form")
                .successHandler(loginAuthenticationSuccessHandler)
                .failureHandler(loginAuthenticationFailureHandler)
                .and()
                .authorizeRequests() //对请求进行授权
                //对signIn.html页面放行
                .antMatchers("/authencation/require",securityPropertities.getLoginPage()).permitAll()
                .antMatchers("/dba/**").hasRole("dba")
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").hasRole("user")
                .anyRequest()  //任何请求
                .authenticated()   //都需要身份认证
                .and()
                .csrf().disable(); //关闭跨站请求伪造防护
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_dba > ROLE_admin \n ROLE_admin > ROLE_user";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }
}
