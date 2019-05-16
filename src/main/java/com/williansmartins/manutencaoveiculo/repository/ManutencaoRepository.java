package com.williansmartins.manutencaoveiculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.williansmartins.manutencaoveiculo.model.Manutencao;

@Repository("manutencaoRepository")
public interface ManutencaoRepository extends JpaRepository<Manutencao, Integer> {
}