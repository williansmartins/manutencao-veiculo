package com.williansmartins.manutencaoveiculo.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.williansmartins.manutencaoveiculo.model.Carro;

public class CarroDAO{

	private final String driver          = "com.mysql.jdbc.Driver";
	private final String url             = "jdbc:mysql://pwms.com.br:3306/waisoc_manutencao";
	private final String user            = "waisoc_manutenca";
	private final String pwd             = "manutencao123";

    public static Connection connection;
    public PreparedStatement prepStatement;
    public static ResultSet  resultSet;

    public void conecta() {

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pwd);
            connection.setAutoCommit(false);
            System.out.println("Conectado");
        } catch (ClassNotFoundException e) {
            System.out.print("Driver não encontrado!\n" + e.getMessage());
        } catch (SQLException e) {
            System.out.print("Erro na Conexao com Banco\n" + e.getMessage());
        }

    }

    public int inserir(String fabricante, String modelo, String ano) {
        try {
            conecta( ) ;

            String sql = "INSERT INTO carros (fabricante, modelo, ano) VALUES ( '" + fabricante + "','" + modelo + "','" + ano + "' )";
            prepStatement = connection.prepareStatement(sql);
            prepStatement.executeUpdate();
    		int id = 0;
            
            try (ResultSet generatedKeys = prepStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("Erro ao obter o id.");
                }
            }
            
            connection.commit();
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
 
    public List<Carro> buscarTudo() {
        try {
            conecta();
            prepStatement = CarroDAO.connection.prepareStatement("Select * from carros ");
            resultSet = prepStatement.executeQuery();
            resultSet.beforeFirst();       
            
            List<Carro> carros = new ArrayList<Carro>();
            
            while (resultSet.next()) {
            	Carro c = new Carro();
            	c.setId( resultSet.getInt("id"));
            	c.setFabricante( resultSet.getString("fabricante"));
            	c.setModelo( resultSet.getString("modelo"));
            	c.setAno( resultSet.getString("ano"));
            	
            	carros.add(c);
            }
            connection.close();
            return carros;
            
        } catch (SQLException ex) {
            System.out.print("Erro na listagem: " + ex.getMessage());
            return new ArrayList<Carro>();
        }finally {
            try { if (prepStatement != null) prepStatement.close(); } catch (Exception e) {};
            try { if (connection != null) connection.close(); } catch (Exception e) {};
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) {};
        }
    }
	
    public int excluir(int id) {
	     try {
	        conecta();
        	String sql = "Delete from carros where id=" + id + "";
        	prepStatement = CarroDAO.connection.prepareStatement(sql);
        	int deuCerto = prepStatement.executeUpdate(sql);
	        
	        connection.commit();
	        System.out.print("Item excluido com sucesso!");
	        return deuCerto;
        }catch (SQLException ex) {
             System.out.println("Erro ao excluir: "+ex.getMessage());
             return 0;
        }finally {
            try { if (prepStatement != null) prepStatement.close(); } catch (Exception e) {};
            try { if (connection != null) connection.close(); } catch (Exception e) {};
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) {};
        }
	}
    
    public int atualizar(Carro carro) {
    	try {
    		conecta();
    		
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
    		
    		connection.commit();
    		System.out.print("Item atualizado com sucesso!");
    		
    		return deuCerto;
    	} catch (SQLException ex) {
    		System.out.println("Erro ao atualizar: " + ex.getMessage());
    		return 0;
    	}finally {
            try { if (prepStatement != null) prepStatement.close(); } catch (Exception e) {};
            try { if (connection != null) connection.close(); } catch (Exception e) {};
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) {};
        }
    }

	public Carro buscarPorId(int id){

    	try{
    		conecta();
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