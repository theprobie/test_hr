package com.example.hrservice.hr.config;

import com.example.hrservice.hr.model.Hr;
import com.example.hrservice.hr.service.UserService;
import com.example.hrservice.hr.util.RespBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Autowired
    MyFilter myFilter;

    @Autowired
    MyDesicionManager myDesicionManager;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {//注意此处配置解决的目的
        web.ignoring().antMatchers("/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                o.setSecurityMetadataSource(myFilter);
                o.setAccessDecisionManager(myDesicionManager);
                return o;
            }
        })
//                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/doLogin")//登录接口
                .loginPage("/login")//如果不登录，将会跳转到这个接口
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse rep, Authentication authentication) throws IOException, ServletException {
                        rep.setContentType("application/json;charset=utf-8");
                        PrintWriter out = rep.getWriter();
                        Hr hr = (Hr) authentication.getPrincipal();
                        hr.setPassword(null);//在前端返回的json中，把密码设置成null
                        RespBean ok = RespBean.ok("登录成功", hr);
                        String s = new ObjectMapper().writeValueAsString(ok);
                        //new ObjectMapper().writeValueAsString()把对象转换成json格式
                        out.write(s);
                        out.flush();
                        out.close();

                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse rep, AuthenticationException e) throws IOException, ServletException {
                        rep.setContentType("application/json:charset=utf-8");
                        PrintWriter out = rep.getWriter();
                        RespBean error = RespBean.error("登录失败");
                        if(e instanceof LockedException){
                            error.setMsg("账户被锁定");
                        }else if(e instanceof CredentialsExpiredException){
                            error.setMsg("密码过期");
                        }else if(e instanceof AccountExpiredException){
                            error.setMsg("账户过期");
                        }else if(e instanceof DisabledException){
                            error.setMsg("账户被禁用");
                        }else if(e instanceof BadCredentialsException){
                            error.setMsg("用户名或者密码错误");
                        }
                        String s = new ObjectMapper().writeValueAsString(error);
                        //new ObjectMapper().writeValueAsString()把对象转换成json格式
                        out.write(s);
                        out.flush();
                        out.close();

                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse rep, Authentication authentication) throws IOException, ServletException {
                        rep.setContentType("application/json;charset=utf-8");
                        PrintWriter out = rep.getWriter();
                        out.write(new ObjectMapper().writeValueAsString(RespBean.ok("注销成功")));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .csrf()
                .disable();
    }
}
