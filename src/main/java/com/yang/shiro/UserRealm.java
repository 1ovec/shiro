package com.yang.shiro;

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
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.yang.entity.User;
import com.yang.service.UserService;

/**
 * 自定义Realm
 * @author 
 *
 */
public class UserRealm extends AuthorizingRealm{
	@Autowired
	private UserService userService;
	/**
	 * 执行认证逻辑
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg) throws AuthenticationException {
		System.out.println("执行认证逻辑");
		//假设用户密码是
//		String userName="admin";
//		String password="123456";
		UsernamePasswordToken token =(UsernamePasswordToken)arg;
		String username = token.getUsername();
		User user = userService.findUserByName(username);
		if(null==user){
			//用户不存在
			return null;//返回null后shiro会抛出用户不存在的异常
		}
		//判断密码  第一个参数是后续从subject.getPrincipal获取的对象
		return new SimpleAuthenticationInfo(user,user.getPassword(),"");
	}
	/**
	 * 执行授权逻辑
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("执行授权逻辑");
		//给资源进行授权
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//添加资源的授权字符串
//		info.addStringPermission("user:add");
		//获取当前用户
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		User dbUser = userService.findUserById(user.getId());
		info.addStringPermission(dbUser.getPerms());
		return info;
	}
}
