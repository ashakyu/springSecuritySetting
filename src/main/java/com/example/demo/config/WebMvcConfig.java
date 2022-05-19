package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	private static String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/static/", "classpath:/public/", "classpath:/",
			"classpath:/resources/", "classpath:/META-INF/resources/", "classpath:/META-INF/resources/webjars/" };

	/*
	 * 정적 자원을 제공하는 클래스를 생성하여 아래와 같이 설정한다.
	 * 정적 자원들에 대해서는 Security 적용을 하지 말아야한다.
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// /에 해당하는 url mapping을 /common/test로 forword한다.
		registry.addViewController("/").setViewName("forward:/index");
		// 우선순위를 가장 높게 잡는다.
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
	}

}
