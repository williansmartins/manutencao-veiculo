package com.williansmartins.manutencaoveiculo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.williansmartins.manutencaoveiculo.model.Carro;

public class Teste {
	public static void main(String[] args) {
		System.out.println(new Teste().buscarTudo().size());
	}

	public List<Carro> buscarTudo() {
		Connection connection = null;
		try {
			connection = DataSource.getConnection();
			PreparedStatement prepStatement = connection.prepareStatement("Select * from carros ");
			ResultSet resultSet = prepStatement.executeQuery();
			resultSet.beforeFirst();

			List<Carro> carros = new ArrayList<Carro>();

			while (resultSet.next()) {
				Carro c = new Carro();
				c.setId(resultSet.getInt("id"));
				c.setFabricante(resultSet.getString("fabricante"));
				c.setModelo(resultSet.getString("modelo"));
				c.setAno(resultSet.getString("ano"));

				carros.add(c);
			}

			System.out.println("Sucesso ao buscar carros.");
			System.out.println(carros);
			return carros;

		} catch (SQLException ex) {
			System.out.print("Erro na listagem: " + ex.getMessage());
			return new ArrayList<Carro>();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
	}
}
