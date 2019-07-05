package com.example.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 权限配置加载
 * 
 * @author liuyong
 */
@Configuration
public class ShiroConfig
{
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    // 登录地址
    @Value("${shiro.user.loginUrl}")
    private String loginUrl;

    // 权限认证失败地址
    @Value("${shiro.user.unauthorizedUrl}")
    private String unauthorizedUrl;


    /**
     * 自定义Realm
     */
    @Bean
    public UserRealm userRealm()
    {
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }
    
//    /**
//     * 缓存管理器 使用Ehcache实现
//     */
//    @Bean
//    public EhCacheManager getEhCacheManager()
//    {
//        net.sf.ehcache.CacheManager cacheManager = net.sf.ehcache.CacheManager.getCacheManager("cost");
//        EhCacheManager em = new EhCacheManager();
//        if (StringUtils.isNull(cacheManager))
//        {
//            em.setCacheManagerConfigFile("classpath:ehcache/ehcache-shiro.xml");
//            return em;
//        }
//        else
//        {
//            em.setCacheManager(cacheManager);
//            return em;
//        }
//    }
//


    /**
     * 安全管理器
     */
    @Bean
    public SecurityManager securityManager(UserRealm userRealm)
    {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(userRealm);
        // 注入缓存管理器;
 //       securityManager.setCacheManager(getEhCacheManager());
        return securityManager;
    }

    /**
     * 退出过滤器
     */
//    public LogoutFilter logoutFilter()
//    {
//        LogoutFilter logoutFilter = new LogoutFilter();
//        logoutFilter.setLoginUrl(loginUrl);
//        return logoutFilter;
//    }

    /**
     * Shiro过滤器配置
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // Shiro的核心安全接口,这个属性是必须的
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 身份认证失败，则跳转到登录页面的配置
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        // 权限认证失败，则跳转到指定页面
        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
        // Shiro连接约束配置，即过滤链的定义
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 对静态资源设置匿名访问
        filterChainDefinitionMap.put("/favicon.ico**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/html/common/iweboffice/**", "anon");
        filterChainDefinitionMap.put("/file/**", "anon");
        //filterChainDefinitionMap.put("/html/**", "anon");
        filterChainDefinitionMap.put("/html/login.html", "anon");
        filterChainDefinitionMap.put("/image/**", "anon");
        // 退出 logout地址，shiro去清除session
        filterChainDefinitionMap.put("/logout", "anon");
        // 不需要拦截的访问
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/**/none", "anon");
        filterChainDefinitionMap.put("/office/weboffice", "anon");
        filterChainDefinitionMap.put("/supplier/listByPage", "anon");
        filterChainDefinitionMap.put("/workFlowLogger/getJumpUrlByParam", "anon");
        filterChainDefinitionMap.put("/websocket/**", "anon");
        // 系统权限列表
        // filterChainDefinitionMap.putAll(SpringUtils.getBean(IMenuService.class).selectPermsAll());

        Map<String, Filter> filters = new LinkedHashMap<>();
        // 注销成功，则跳转到指定页面
        //filters.put("logout", logoutFilter());
        shiroFilterFactoryBean.setFilters(filters);

        // 所有请求需要认证
        filterChainDefinitionMap.put("/**", "user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 开启Shiro注解通知器
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("securityManager") SecurityManager securityManager)
    {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
