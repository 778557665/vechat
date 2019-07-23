package com.wengzhoujun.vechat;

import com.wengzhoujun.vechat.netty.server.core.ImServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VechatApplication {

	public static void main(String[] args) {
		SpringApplication.run(VechatApplication.class, args);
	}

}
