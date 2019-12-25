package com.yang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.yang.mapper")
public class Application{ // extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer
  public static void main(String[] args) {
    SpringApplication.run(Application.class,args);
  }


  /*@Override
  public void customize(ConfigurableEmbeddedServletContainer container) {
    //指定项目名称
    container.setContextPath("/");
    //指定端口地址
    container.setPort(8090);
  }*/
}
