package com.williansmartins.manutencaoveiculo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.williansmartins.manutencaoveiculo.model.Manutencao;
import com.williansmartins.manutencaoveiculo.repository.ManutencaoRepository;
import com.williansmartins.manutencaoveiculo.service.ManutencaoService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ManutencaoServiceIntegratedTest {

    private Manutencao manutencao;

    @Autowired
    private ManutencaoService manutencaoService;
    @Autowired
    private ManutencaoRepository manutencaoRepository;

    @Before
    public void setUp() {
        
    }
    
    @Test
    public void inserir() {
    	manutencao = new Manutencao();
		manutencao.setKilometragem(123);
		manutencaoRepository.save(manutencao);
    }

    @Test
    public void updateManutencao() {
		manutencao = new Manutencao();
		manutencao.setId(2);
		manutencao.setKilometragem(123);
		manutencaoService.saveManutencao(manutencao);
    }
    
}
