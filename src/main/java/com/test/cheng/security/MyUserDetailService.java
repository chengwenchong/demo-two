package com.test.cheng.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.test.cheng.domain.MyUser;
import com.test.cheng.service.UserService;

public class MyUserDetailService implements UserDetailsService{

	@Autowired 
    private UserService userService;  

    //登陆验证时，通过username获取用户的所有权限信息，  
    //并返回User放到spring的全局缓存SecurityContextHolder中，以供授权器使用  
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {     
        Collection<GrantedAuthority> auths= new ArrayList<GrantedAuthority>();  
        //获取角色标志  
        String roletype = username.substring(0,3);  
        username = username.substring(3);  
        String password = "";  

        if("stu".equals(roletype)){  
            MyUser stu = userService.findById(username);  
            password = stu.getPassword();  
            auths.add(new SimpleGrantedAuthority("ROLE_STU"));  
        }else if("tea".equals(roletype)){  
        	MyUser tea = userService.findById(username);  
            password = tea.getPassword();  
            auths.add(new SimpleGrantedAuthority("ROLE_TEA"));  
        }else if("adm".equals(roletype)){  
        	MyUser adm = userService.findById(username);  
            password = adm.getPassword();  
            auths.add(new SimpleGrantedAuthority("ROLE_ADM"));  
        }  

        User user = new User(username, password, true, true, true, true, auths);   
        return user;    
    }  

}
