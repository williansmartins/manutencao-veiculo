package com.williansmartins.manutencaoveiculo.model;

public enum Categoria {
	COMBUSTIVEL("Combustível"), 
	TROCADEOLEO("Troca de óleo"), 
	PNEUDIANTEIRO("Pneu dianteiro"), 
	PNEUTRASEIRO("Pneu traseiro"), 
	CORRENTE("Corrente"), 
	RELACAO("Relação");
	
	private String label;

	Categoria(String item){
		this.label = item;
	}
	
	public String getLabel(){
		return this.label;
	}
	
}
