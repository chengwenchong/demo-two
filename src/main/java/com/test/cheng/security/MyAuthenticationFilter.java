package com.test.cheng.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.test.cheng.domain.MyUser;
import com.test.cheng.service.UserService;

public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	private static final String USERNAME = "username";  
    private static final String PASSWORD = "password";  

    @Autowired 
    private UserService userService; 

    @Override 
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {  
            if (!request.getMethod().equals("POST")) {  
                throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());  
            }  

            String username = obtainUsername(request);  
            String password = obtainPassword(request);  
            String roletype = request.getParameter("roletype");  

            username = username.trim();  

            UsernamePasswordAuthenticationToken authRequest = null;  

            if(!"".equals(roletype) || roletype != null){  
                if("student".equals(roletype)){  
                    MyUser stu = userService.findById(username);  

                    //ͨ��session���û��������õ�session��  
                    request.getSession().setAttribute("session_user", stu);  

                    //����ɫ��־��username��  
                    username = "stu"+username;  

                    try {  
                        if (stu == null || !stu.getPassword().equals(password)) {  
                            BadCredentialsException exception = new BadCredentialsException("�û��������벻ƥ��");  
                            throw exception;  
                        }  
                    } catch (Exception e) {  
                        BadCredentialsException exception = new BadCredentialsException("û�д��û�");  
                        throw exception;  
                    }  

                }else if("teacher".equals(roletype)){  
                    MyUser tea = userService.findById(username);  

                    //ͨ��session���û��������õ�session��  
                    request.getSession().setAttribute("session_user", tea);  

                    //����ɫ��־��username��  
                    username = "tea"+username;  

                    try {  
                        if (tea == null || !tea.getPassword().equals(password)) {  
                            BadCredentialsException exception = new BadCredentialsException("�û��������벻ƥ��");  
                            throw exception;  
                        }  
                    } catch (Exception e) {  
                        BadCredentialsException exception = new BadCredentialsException("û�д��û�");  
                        throw exception;  
                    }  

                }else if("admin".equals(roletype)){  
                	MyUser adm = userService.findById(username);  

                    //ͨ��session���û��������õ�session��  
                    request.getSession().setAttribute("session_user", adm);  

                    //����ɫ��־��username��  
                    username = "adm"+username;  
                    try {  
                        if (adm == null || !password.equals(adm.getPassword())) {  
                            BadCredentialsException exception = new BadCredentialsException("�û��������벻ƥ��");  
                            throw exception;  
                        }  
                    } catch (Exception e) {  
                        BadCredentialsException exception = new BadCredentialsException("û�д��û�");  
                        throw exception;  
                    }  

                }else{  
                    BadCredentialsException exception = new BadCredentialsException("ϵͳ����û�ж�Ӧ�Ľ�ɫ��");  
                    throw exception;  
                }  
            }  

            //ʵ����֤  
            authRequest = new UsernamePasswordAuthenticationToken(username, password);  
            //���������û���ϸ����  
            setDetails(request, authRequest);  
            //����  
            return this.getAuthenticationManager().authenticate(authRequest);  
    }  

    @Override 
    protected String obtainUsername(HttpServletRequest request) {  
        Object obj = request.getParameter(USERNAME);  
        return null == obj ? "" : obj.toString();  
    }  

    @Override 
    protected String obtainPassword(HttpServletRequest request) {  
        Object obj = request.getParameter(PASSWORD);  
        return null == obj ? "" : obj.toString();  
    }  

    @Override 
    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {  
        super.setDetails(request, authRequest);  
    } 
}
