package com.wengzhoujun.vechat.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/7/9.
 *
 * @author WengZhoujun
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "ignored")
public class IgnoredUrlsConfig {

    private List<String> urls = new ArrayList<>();
}
