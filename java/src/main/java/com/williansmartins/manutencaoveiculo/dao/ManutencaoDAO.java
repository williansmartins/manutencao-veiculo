package com.williansmartins.manutencaoveiculo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.williansmartins.manutencaoveiculo.config.DataSource;
import com.williansmartins.manutencaoveiculo.model.Categoria;
import com.williansmartins.manutencaoveiculo.model.Manutencao;

public class ManutencaoDAO {

	public int inserir(int id_veiculo, int id_usuario, Date data, Categoria categoria, int kilometragem,
			String observacoes) {
		Connection connection = null;
		PreparedStatement prepStatement = null;

		try {
			connection = DataSource.getConnection();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String dataFormatada = dateFormat.format(date);

			String sql = "INSERT INTO manutencoes (id_veiculo, id_usuario, data, categoria, kilometragem, observacoes) VALUES ( '"
					+ id_veiculo + "','" + id_usuario + "','" + dataFormatada + "','" + categoria + "','" + kilometragem + "','"
					+ observacoes + "')";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.executeUpdate();
			int id = 0;

			ResultSet generatedKeys = prepStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			} else {
				throw new SQLException("Erro ao obter o id.");
			}

			System.out.println("Inserido com sucesso");
			return id;

		} catch (SQLException ex) {
			System.out.print("Erro ao inserir: " + ex.getMessage());
			return 0;
		} finally {
			try {
				if (prepStatement != null)
					prepStatement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
	}

	public List<Manutencao> buscarTudo() {
		Connection connection = null;
		try {
			connection = DataSource.getConnection();
			PreparedStatement prepStatement = connection.prepareStatement("Select * from manutencoes");
			ResultSet resultSet = prepStatement.executeQuery();
			resultSet.beforeFirst();

			List<Manutencao> lista = new ArrayList<Manutencao>();

			while (resultSet.next()) {
				Manutencao objeto = new Manutencao();
				objeto.setId(resultSet.getInt("id"));
				objeto.setId_veiculo(resultSet.getInt("id_veiculo"));
				objeto.setId_usuario(resultSet.getInt("id_usuario"));
				objeto.setData(resultSet.getDate("data"));
				objeto.setCategoria(Categoria.valueOf(resultSet.getString("categoria")));
				objeto.setKilometragem(resultSet.getInt("kilometragem"));
				objeto.setObservacoes(resultSet.getString("observacoes"));

				lista.add(objeto);
			}

			System.out.println("Sucesso ao buscar tudo.");
			System.out.println(lista);
			return lista;

		} catch (SQLException ex) {
			System.out.print("Erro ao buscar tudo: " + ex.getMessage());
			return new ArrayList<Manutencao>();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
	}

	public int excluir(int id) {
		Connection connection = null;
		PreparedStatement prepStatement = null;
		try {
			connection = DataSource.getConnection();
			String sql = "Delete from manutencoes where id=" + id + "";
			prepStatement = connection.prepareStatement(sql);
			int deuCerto = prepStatement.executeUpdate(sql);

			System.out.print("Item excluido com sucesso! ");
			return deuCerto;
		} catch (SQLException ex) {

			System.out.println("Erro ao excluir: " + ex.getMessage());
			return 0;
		} finally {
			try {
				if (prepStatement != null)
					prepStatement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
	}

	public int atualizar(Manutencao objeto) {
		Connection connection = null;
		PreparedStatement prepStatement = null;

		try {
			connection = DataSource.getConnection();
			List<String> lista = new ArrayList<String>();

			if (objeto.getId() != 0) {
				lista.add("id = '" + objeto.getId() + "'");
			}

			if (objeto.getId_veiculo() != 0) {
				lista.add("id_veiculo = '" + objeto.getId_veiculo() + "'");
			}

			if (objeto.getId_usuario() != 0) {
				lista.add("id_usuario = '" + objeto.getId_usuario() + "'");
			}

			if (objeto.getData() != null) {
				lista.add("data = '" + objeto.getData() + "'");
			}

			if (objeto.getCategoria() != null) {
				lista.add("categoria = '" + objeto.getCategoria() + "'");
			}

			if (objeto.getKilometragem() != 0) {
				lista.add("kilometragem = '" + objeto.getKilometragem() + "'");
			}

			if (objeto.getObservacoes() != null) {
				lista.add("observacoes = '" + objeto.getObservacoes() + "'");
			}

			String sql = "UPDATE manutencoes SET " + String.join(", ", lista) + " WHERE id=" + objeto.getId() + "";

			prepStatement = connection.prepareStatement(sql);
			int deuCerto = prepStatement.executeUpdate();

			System.out.println("Item atualizado com sucesso!");

			return deuCerto;
		} catch (SQLException ex) {
			System.out.println("Erro ao atualizar: " + ex.getMessage());
			return 0;
		} finally {
			try {
				if (prepStatement != null)
					prepStatement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
	}

	public Manutencao buscarPorId(int id) {
		Connection connection = null;
		PreparedStatement prepStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DataSource.getConnection();

			String sql = "select * from manutencoes where id = " + id + " ";
			prepStatement = connection.prepareStatement(sql);
			resultSet = prepStatement.executeQuery();

			resultSet.first();

			Manutencao objeto = new Manutencao();
			objeto.setId(resultSet.getInt("id"));
			objeto.setId_veiculo(resultSet.getInt("id_veiculo"));
			objeto.setId_usuario(resultSet.getInt("id_usuario"));
			objeto.setData(resultSet.getDate("data"));
			objeto.setCategoria(Categoria.valueOf(resultSet.getString("categoria")));
			objeto.setKilometragem(resultSet.getInt("kilometragem"));
			objeto.setObservacoes(resultSet.getString("observacoes"));

			return objeto;
		} catch (SQLException ex) {
			System.out.print("Erro ao preparar: " + ex.getMessage());
			return new Manutencao();
		} finally {
			try {
				if (prepStatement != null)
					prepStatement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
			}
			;
		}

	}
}