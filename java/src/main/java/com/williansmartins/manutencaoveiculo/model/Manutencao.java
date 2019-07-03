package com.williansmartins.manutencaoveiculo.model;

import java.io.Serializable;
import java.util.Date;

public class Manutencao implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private int id_veiculo;
	private int id_usuario;
	private Date data;
	private Categoria categoria;
	private int kilometragem;
	private String observacoes;
	@SuppressWarnings("unused")
	private String categoriaHumanizada;

	public Manutencao() {
		System.out.println("aqui");
	}

	public Manutencao(int id, int id_veiculo, int id_usuario, Date data, Categoria categoria, int kilometragem, String observacoes) {
		super();
		this.id = id;
		this.id_veiculo = id_veiculo;
		this.id_usuario = id_usuario;
		this.data = data;
		this.categoria = categoria;
		this.kilometragem = kilometragem;
		this.observacoes = observacoes;
		System.out.println("aqui 2");
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public int getKilometragem() {
		return kilometragem;
	}

	public void setKilometragem(int kilometragem) {
		this.kilometragem = kilometragem;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public int getId_veiculo() {
		return id_veiculo;
	}

	public void setId_veiculo(int id_veiculo) {
		this.id_veiculo = id_veiculo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	public String getCategoriaHumanizada() {
		return categoria.getLabel();
	}

}
