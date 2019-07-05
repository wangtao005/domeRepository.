package com.example.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.alibaba.fastjson.JSONObject;

/**
 * shiro 工具类
 * 
 * @author liuyong
 */
public class ShiroUtils
{
    public static Subject getSubjct()
    {
        return SecurityUtils.getSubject();
    }

    public static Session getSession()
    {
        return SecurityUtils.getSubject().getSession();
    }

    public static void logout()
    {
        getSubjct().logout();
    }
    
    public static JSONObject getSessionUserInfo()
    {
    	return (JSONObject)getSession().getAttribute("sessionUserInfo");
    }


    public static void clearCachedAuthorizationInfo()
    {
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        UserRealm realm = (UserRealm) rsm.getRealms().iterator().next();
        realm.clearCachedAuthorizationInfo();
    }

    public static String getUserId()
    {
        return getSessionUserInfo().getString("id");
    }

    public static String getUserName()
    {
    	return getSessionUserInfo().getString("name");
    }
    
    public static boolean isAdmin()
    {
        return "1".equals(getUserId());
    }

    public static String getIp()
    {
        return getSubjct().getSession().getHost();
    }
    
    public static String getToken()
    {
    	return (String)getSubjct().getSession().getAttribute("sessionToken");
    }

    public static String getSessionId()
    {
        return String.valueOf(getSubjct().getSession().getId());
    }

    /**
     * 生成随机盐
     */
    public static String randomSalt()
    {
        // 一个Byte占两个字节，此处生成的3字节，字符串长度为6
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        String hex = secureRandom.nextBytes(3).toHex();
        return hex;
    }
}
