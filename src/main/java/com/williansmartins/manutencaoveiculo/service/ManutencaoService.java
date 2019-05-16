package com.williansmartins.manutencaoveiculo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.williansmartins.manutencaoveiculo.model.Manutencao;
import com.williansmartins.manutencaoveiculo.repository.ManutencaoRepository;

@Service("manutencaoService")
public class ManutencaoService {

    private ManutencaoRepository manutencaoRepository;

    @Autowired
    public ManutencaoService(ManutencaoRepository manutencaoRepository) {
    	this.manutencaoRepository = manutencaoRepository;
    }

    public Manutencao saveManutencao(Manutencao manutencao) {
        return manutencaoRepository.save(manutencao);
    }
    
    public void deleteManutencao(Integer id) {
        manutencaoRepository.deleteById(id);
    }
    
    public List<Manutencao> findAll() {
        return manutencaoRepository.findAll();
    }
    
    public Optional<Manutencao> findById(Integer id) {
        return manutencaoRepository.findById(id);
    }
    
}