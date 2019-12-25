package com.yang.control;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yang.service.UserService;

@Controller
public class ThymeleafControl {
	
	@RequestMapping("/test")
	public String testThymeleaf(Model model) {
		model.addAttribute("value", "Hello这是主页");
		return "/index";
	}
	@RequestMapping("/login")
	public String login(String name,String password,Model model) {
		/**
		 * 使用shiro认证操作
		 */
		//1、拿到subject
		Subject subject = SecurityUtils.getSubject();
		//2、封装用户数据
		UsernamePasswordToken token = new UsernamePasswordToken(name, password);
		//3、执行登录方法
		try {
			subject.login(token);
			System.out.println("登录成功");
		} catch (UnknownAccountException e) {
			model.addAttribute("msg","登录失败，用户不存在");
			return "login";
		}catch (IncorrectCredentialsException e) {
			model.addAttribute("msg","登录失败，密码错误");
			return "login";
		}
		return "redirect:test";
	}
	@RequestMapping("/add")
	public String add() {
		return "/user/add";
	}
	@RequestMapping("/update")
	public String update() {
		return "/user/update";
	}
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "/login";
	}
	@RequestMapping("/noAuth")
	public String noAuth() {
		return "/user/noAuth";
	}
	@RequestMapping("/loginOut")
	public String loginOut() {
		SecurityUtils.getSubject().logout();
		return "redirect:toLogin";
	}
}
