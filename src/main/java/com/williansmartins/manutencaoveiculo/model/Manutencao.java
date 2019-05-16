package com.williansmartins.manutencaoveiculo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "manutencao")
public class Manutencao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;
	private int id_veiculo;
	private int id_usuario;
	private Date data;
	private Categoria categoria;
	private int kilometragem;

	public Manutencao() {

	}

	public Manutencao(int id, int id_veiculo, int id_usuario, Date data, Categoria categoria, int kilometragem) {
		super();
		this.id = id;
		this.id_veiculo = id_veiculo;
		this.id_usuario = id_usuario;
		this.data = data;
		this.categoria = categoria;
		this.kilometragem = kilometragem;
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

}

enum Categoria {

}