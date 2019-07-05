package com.williansmartins.manutencaoveiculo.model;

public enum Categoria {
	COMBUSTIVEL("Combustível"), 
	TROCADEOLEO("Troca de óleo"), 
	PNEUDIANTEIRO("Pneu dianteiro"), 
	PNEUTRASEIRO("Pneu traseiro"), 
	CORRENTE("Corrente"), 
	RELACAO("Relação"),
	FILTRODECOMBUSTIVEL("Filtro de Combustível");
	
	
	private String item;

	Categoria(String item){
		this.item = item;
	}
	
	public String getItem(){
		return this.item;
	}

}
