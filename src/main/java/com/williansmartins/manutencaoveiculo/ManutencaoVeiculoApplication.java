package com.williansmartins.manutencaoveiculo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.williansmartins.manutencaoveiculo.repository.*")
@ComponentScan(basePackages = { "com.williansmartins.manutencaoveiculo.*" })
@EntityScan("com.williansmartins.manutencaoveiculo.model.*")   
public class ManutencaoVeiculoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManutencaoVeiculoApplication.class, args);
	}

}
