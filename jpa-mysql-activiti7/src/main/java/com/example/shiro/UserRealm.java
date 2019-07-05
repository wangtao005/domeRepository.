package com.example.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 自定义shiro实体
 * @author Zhang lu sheng
 * @date 2019年4月19日
 * @Desc
 */
public class UserRealm extends AuthorizingRealm {
	private static final Logger log = LoggerFactory.getLogger(UserRealm.class);
	//是否需要登录(登录方式：直接账号密码登录 | Session同步)
	private boolean isLogin = true;
	
//	@Autowired
//    private LoginService loginService;

	/**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
    	SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    	if(ShiroUtils.isAdmin()) {
    		info.addRole("admin");
    		info.addStringPermission("*:*:*");
    	}else {
    		JSONObject sessionUserInfo = (JSONObject)ShiroUtils.getSession().getAttribute("sessionUserInfo");
    		JSONArray roleList = sessionUserInfo.getJSONArray("roleList");
    		for(int i = 0; i < roleList.size(); i++) {
    			JSONArray permissions = roleList.getJSONObject(i).getJSONArray("permissions");
    			if(permissions != null) {
    				for(int j = 0; j < permissions.size(); j++) {
        				String permission = permissions.getString(j);
        				if(StringUtils.isNotEmpty(permission)) {
        					info.addStringPermission(permission);
        				}
        			}
    			}
    		}
    	}
        return info;
    }

	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		String password = "";
		if (upToken.getPassword() != null) {
			password = new String(upToken.getPassword());
		}
		
		if(isLogin) {
			try {
//				loginService.login(username, password);
			}catch (Exception e) {
				log.info("对用户[" + username + "]进行登录验证..验证未通过：{}", e.getMessage());
	            throw new AuthenticationException(e.getMessage(), e);
			}
		}

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());
		return info;
	}

	/**
	 * 清理缓存权限
	 */
	public void clearCachedAuthorizationInfo() {
		this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
	}
}
