package com.williansmartins.manutencaoveiculo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc //mvc:annotation-driven
@Configuration
@ComponentScan({"com.williansmartins.manutencaoveiculo.controller"})
public class WebConfig extends  WebMvcConfigurerAdapter {

}

