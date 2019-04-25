package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 重新写configure(HttpSecurity http),目的是不让spring Security验证登录
 * 
 * @author Administrator
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
	        .authorizeRequests().anyRequest().permitAll()
	        .and().headers().frameOptions().sameOrigin().and().csrf().disable();
//            .authorizeRequests()
//            	.antMatchers("/**","/").permitAll()
//                .anyRequest().authenticated();
////                .and()
//            .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//            .logout()
//                .permitAll();
        
        //.csrf().disable()
        //如果在项目中加入了 Spring Security 做安全控制，那么 CSRF 保护默认是开启的，
        //那么在 POST 方式提交表单的时候就必须验证 Token，如果没有，那么自然也就是 403 没权限了。
        
        
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//                .withUser("admin").password("123456").roles("USER");
//    }
//   
}
