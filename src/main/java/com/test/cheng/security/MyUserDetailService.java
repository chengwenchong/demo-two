package com.test.cheng.security;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.test.cheng.dao.IAdminDao;
import com.test.cheng.dao.IStudentDao;
import com.test.cheng.dao.ITeacherDao;
import com.test.cheng.domain.Admin;
import com.test.cheng.domain.Student;
import com.test.cheng.domain.Teacher;

public class MyUserDetailService implements UserDetailsService{

	@Resource 
    private IStudentDao studentdao;  
    @Resource 
    private ITeacherDao teacherdao;  
    @Resource 
    private IAdminDao admindao;  

    //��½��֤ʱ��ͨ��username��ȡ�û�������Ȩ����Ϣ��  
    //������User�ŵ�spring��ȫ�ֻ���SecurityContextHolder�У��Թ���Ȩ��ʹ��  
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {     
        Collection<GrantedAuthority> auths= new ArrayList<GrantedAuthority>();  
        //��ȡ��ɫ��־  
        String roletype = username.substring(0,3);  
        username = username.substring(3);  
        String password = "";  

        if("stu".equals(roletype)){  
            Student stu = studentdao.findById(username);  
            password = stu.getPassword();  
            auths.add(new SimpleGrantedAuthority("ROLE_STU"));  
        }else if("tea".equals(roletype)){  
            Teacher tea = teacherdao.findById(username);  
            password = tea.getPassword();  
            auths.add(new SimpleGrantedAuthority("ROLE_TEA"));  
        }else if("adm".equals(roletype)){  
            Admin adm = admindao.findById(username);  
            password = adm.getPassword();  
            auths.add(new SimpleGrantedAuthority("ROLE_ADM"));  
        }  

        User user = new User(username, password, true, true, true, true, auths);   
        return user;    
    }  

}
