package com.williansmartins.manutencaoveiculo.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc //mvc:annotation-driven
@Configuration
@ComponentScan({"com.williansmartins.manutencaoveiculo.controller"})
@EnableCaching
public class WebConfig extends  WebMvcConfigurerAdapter {

	@Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("addresses");
    }
}

