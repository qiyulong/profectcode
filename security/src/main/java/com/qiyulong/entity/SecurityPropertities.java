package com.qiyulong.entity;

import com.qiyulong.config.LoginType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "security")
public class SecurityPropertities {
    String loginPage = "/login.html";
    LoginType loginType= LoginType.JSON;
}
