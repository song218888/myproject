package com.tiancheng.springboot.chaptor02.lesson02.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiancheng.springboot.chaptor02.lesson02.bean.ConfigBean;
import com.tiancheng.springboot.chaptor02.lesson02.bean.User;

@RestController
@EnableConfigurationProperties({ConfigBean.class,User.class})
public class LucyController {
    @Autowired
    ConfigBean configBean;
    
    @Autowired
    User user;
    
    @RequestMapping(value = "/user")
    public String user(){
        return user.getName()+user.getAge();
    }

    @RequestMapping(value = "/lucy")
    public String miya(){
        return configBean.getGreeting()+" >>>>"+configBean.getName()+" >>>>"+ configBean.getUuid()+" >>>>"+configBean.getMax();
    }

}
