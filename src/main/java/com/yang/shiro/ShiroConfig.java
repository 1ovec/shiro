package com.yang.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yang.service.UserService;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
/**
 * shiro配置类
 * @author yWX547898
 *
 */
@Configuration
public class ShiroConfig {
	/**
	 * 创建ShiroFilterFactoryBean
	 */
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		//设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
		//添加shiro内置过滤器
		/**
		 * shiro内置过滤器可以实现权限相关的拦截器
		 * 常用的过滤器有：
		 * 	anon：无需认证（登录）就可以访问
		 * 	authc：必须认证才可以访问
		 * 	user：如果使用rememberMe的功能可以直接访问
		 * 	perms：该资源必须得到资源权限才可以访问
		 *  role：该资源必须得到角色权限才可以访问
		 */
		Map<String, String> filterMap=new LinkedHashMap<String, String>();
//		filterMap.put("/add", "authc");//key是访问资源，value是拦截器类型(一个个拦截)
//		filterMap.put("/update", "authc");
		filterMap.put("/test", "anon");//不拦截的要放在前面，注意顺序
		filterMap.put("/login", "anon");//登录不要拦截
		//添加授权过滤器
		filterMap.put("/add", "perms[user:add]");
		filterMap.put("/update", "perms[user:update]");
		filterMap.put("/*", "authc");
		
		
		//修改跳转登录页面地址 默认是login.jsp
		shiroFilterFactoryBean.setLoginUrl("/toLogin");
		//修改未授权页面
		shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
		return shiroFilterFactoryBean;
	}
	/**
	 * 创建DefaultWebSecurityManager
	 */
	@Bean(name="defaultWebSecurityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		//关联自定义realm
		securityManager.setRealm(userRealm);
		return securityManager;
	}
	/**
	 * 创建realm
	 */
	@Bean(name="userRealm")
	public UserRealm getRealm() {
		return new UserRealm();
	}
	/**
	 * 创建ShiroDialect,用于thymleaf和shiro标签配合使用
	 */
	@Bean
	public ShiroDialect getShiroDialect() {
		return new ShiroDialect();
	}
}
