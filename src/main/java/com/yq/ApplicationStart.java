package com.yq;

import com.yq.conf.DruidConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * created by wb-yq264139 on 2017/11/6
 */
@SpringBootApplication
@EnableConfigurationProperties({DruidConfiguration.class})
public class ApplicationStart {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);
    }
}
