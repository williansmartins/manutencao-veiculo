package com.williansmartins.manutencaoveiculo.model;

public enum Status {
	
	SUCESSO("Sucesso"),
	ERRO("Erro");
	
	private String descricao;
	
	Status(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
