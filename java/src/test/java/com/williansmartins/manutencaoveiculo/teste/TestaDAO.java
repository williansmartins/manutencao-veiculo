package com.williansmartins.manutencaoveiculo.teste;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.williansmartins.manutencaoveiculo.dao.ManutencaoDAO;
import com.williansmartins.manutencaoveiculo.model.Manutencao;

public class TestaDAO {
	
	@Test
	public void buscarTudo() {
		ManutencaoDAO manutencaoDAO = new ManutencaoDAO();
		List<Manutencao> lista = manutencaoDAO.buscarTudo();
		Assert.assertTrue(lista.size() > 1);
	}
}
