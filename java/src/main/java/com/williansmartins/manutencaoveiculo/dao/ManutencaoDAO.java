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
import com.williansmartins.manutencaoveiculo.model.Carro;
import com.williansmartins.manutencaoveiculo.model.Categoria;
import com.williansmartins.manutencaoveiculo.model.Manutencao;

public class ManutencaoDAO{

    public int inserir( int id_veiculo, int id_usuario, Date data, Categoria categoria, int kilometragem, String observacoes) {
    	Connection connection = null;
    	PreparedStatement prepStatement = null;
    	
		try {
			connection = DataSource.getConnection();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String dataFormatada = df.format(data); 

            String sql = "INSERT INTO manutencoes (  id_veiculo,  id_usuario,  data,  categoria, kilometragem,  observacoes) VALUES ( '" + id_veiculo + "','" + id_usuario + "','" + dataFormatada + "','" + categoria + "','" + kilometragem + "','" + observacoes + "' )";
            prepStatement = connection.prepareStatement(sql);
            prepStatement.executeUpdate();
    		int id = 0;
            
            ResultSet generatedKeys = prepStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Erro ao obter o id.");
            }
            
            System.out.println("Inserido com sucesso");
            return id;

        } catch (SQLException ex) {
            System.out.print("Erro ao inserir: " + ex.getMessage());
            return 0;
        }finally {
            try { if (prepStatement != null) prepStatement.close(); } catch (Exception e) {};
            try { if (connection != null) connection.close(); } catch (Exception e) {};
        }
    }
 
    public List<Manutencao> buscarTudo() {
    	Connection connection = null;
		try {
			connection = DataSource.getConnection();
            PreparedStatement prepStatement = connection.prepareStatement("Select * from manutencoes ");
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
         
            System.out.println("Sucesso ao buscar carros.");
            System.out.println(lista);
            return lista;
            
        } catch (SQLException ex) {
            System.out.print("Erro na listagem: " + ex.getMessage());
            return new ArrayList<Manutencao>();
        }finally {
            try { if (connection != null) connection.close(); } catch (Exception e) {};
        }
    }
	
    public int excluir(int id) {
    	Connection connection = null;
    	PreparedStatement prepStatement = null;
		try {
			connection = DataSource.getConnection();
        	String sql = "Delete from carros where id=" + id + "";
        	prepStatement = connection.prepareStatement(sql);
        	int deuCerto = prepStatement.executeUpdate(sql);
	        
	        System.out.print("Item excluido com sucesso! ");  
	        return deuCerto;
        }catch (SQLException ex) {
        	
             System.out.println("Erro ao excluir: "+ex.getMessage());
             return 0;
        }finally {
            try { if (prepStatement != null) prepStatement.close(); } catch (Exception e) {};
            try { if (connection != null) connection.close(); } catch (Exception e) {};
        }
	}
    
    public int atualizar(Carro carro) {
    	Connection connection = null;
    	PreparedStatement prepStatement = null;
    	
		try {
			connection = DataSource.getConnection();
    		
    		String fabricanteSQL = "";
    		String modeloSQL = "";
    		String anoSQL = "";
    		List<String> lista = new ArrayList<String>();
    		
    		if(carro.getFabricante() != null) {
    			fabricanteSQL = "fabricante = '"+ carro.getFabricante() +"'";
    			lista.add(fabricanteSQL);
    		}
    		
    		if(carro.getModelo() != null) {
    			modeloSQL = "modelo = '"+ carro.getModelo() +"'";
    			lista.add(modeloSQL);
    		}
    		
    		if(carro.getAno() != null) {
    			anoSQL = "ano = '"+ carro.getAno() +"'";
    			lista.add(anoSQL);
    		}
    		
    		String sql = "UPDATE carros SET " + String.join(", ", lista) + " WHERE id=" + carro.getId() + "";
    		    		
    		prepStatement = connection.prepareStatement(sql);
    		int deuCerto = prepStatement.executeUpdate();
    		
    		System.out.println("Item atualizado com sucesso!");
    		
    		return deuCerto;
    	} catch (SQLException ex) {
    		System.out.println("Erro ao atualizar: " + ex.getMessage());
    		return 0;
    	}finally {
            try { if (prepStatement != null) prepStatement.close(); } catch (Exception e) {};
            try { if (connection != null) connection.close(); } catch (Exception e) {};
        }
    }

	public Carro buscarPorId(int id){
		Connection connection = null;
    	PreparedStatement prepStatement = null;
    	ResultSet resultSet = null;
    	
		try {
			connection = DataSource.getConnection();
			
    		String sql = "select * from carros where id = "+id+" ";
    		prepStatement = connection.prepareStatement(sql);
    		resultSet = prepStatement.executeQuery(); 

    		resultSet.first();

    		Carro carro = new Carro();

    		carro.setId(resultSet.getInt("id"));
    		carro.setFabricante(resultSet.getString("fabricante"));
    		carro.setModelo(resultSet.getString("modelo"));
    		carro.setAno(resultSet.getString("ano"));
            
            return carro;
        }
        catch (SQLException ex) {
            System.out.print("Erro ao preparar: " + ex.getMessage());
            return new Carro();
        }finally {
            try { if (prepStatement != null) prepStatement.close(); } catch (Exception e) {};
            try { if (connection != null) connection.close(); } catch (Exception e) {};
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) {};
        }
	        
   }
}