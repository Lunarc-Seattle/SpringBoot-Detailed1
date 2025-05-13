package com.devitro.quickstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //“服务窗口”，比如：你访问 /hello，它就“回应”你一个字符串或者 JSON 数据；它不会返回网页，也不会跳转页面，而是直接把数据甩给你。
public class QuickstartApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickstartApplication.class, args);
	}

}
