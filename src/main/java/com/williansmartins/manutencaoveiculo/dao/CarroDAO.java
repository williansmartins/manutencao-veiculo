package com.williansmartins.manutencaoveiculo.dao;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.williansmartins.manutencaoveiculo.model.Carro;

public class CarroDAO{

	private final String driver          = "com.mysql.jdbc.Driver";
	private final String url             = "jdbc:mysql://pwms.com.br:3306/waisoc_manutencao";
	private final String user            = "waisoc_manutenca";
	private final String pwd             = "manutencao123";

//	final String driver          = "com.mysql.jdbc.Driver";
//    final String url             = "jdbc:mysql://localhost:3306/manutencao_veiculo";
//    final String user            = "root";
//    final String pwd             = "";
    
    private static ComboPooledDataSource cpds;
    
	public void getNumberConnection()  {
	        
	    	try (Connection connection =
	                DriverManager.getConnection(url, user, pwd)) {
	
	           // Get database meta data.
	           DatabaseMetaData metaData = connection.getMetaData();
	
	           // Retrieves the maximum number of concurrent
	           // connections to this database that are possible.
	           // A result of zero means that there is no limit or
	           // the limit is not known.
	           int max = metaData.getMaxConnections();
	           System.out.println("Max concurrent connections: " + max);
	       } catch (SQLException e) {
	           e.printStackTrace();
	       }
	}

    public Connection getConnection()  {
        
    	try {
			if (cpds == null) {
			    cpds = new ComboPooledDataSource();
			    cpds.setDriverClass(driver);
			    cpds.setJdbcUrl(url);
			    cpds.setUser(user);
			    cpds.setPassword(pwd);
			    cpds.setMinPoolSize(1); 
			    cpds.setAcquireIncrement(2); 
			    cpds.setMaxPoolSize(6);
			    cpds.setAcquireRetryAttempts(3);
			    //cpds.setMaxIdleTime(60);
			}
			System.out.println("Pool forneceu conexão.");
			return cpds.getConnection();
		} catch (Exception e) {
			System.out.println("Não forneceu conexão.");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
    }
    
    public Connection getConnection2() {

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, pwd);
            connection.setAutoCommit(false);
            System.out.println("Conectado");
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.print("Driver nao encontrado!\n" + e.getMessage());
        } catch (SQLException e) {
            System.out.print("Erro na Conexao com Banco\n" + e.getMessage());
        }
        
        return null;

    }

    public int inserir(String fabricante, String modelo, String ano) {
    	Connection connection = null;
    	PreparedStatement prepStatement = null;
    	
		try {
			connection = C3poDataSource.getConnection();

            String sql = "INSERT INTO carros (fabricante, modelo, ano) VALUES ( '" + fabricante + "','" + modelo + "','" + ano + "' )";
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
 
    public List<Carro> buscarTudo() {
    	Connection connection = null;
		try {
			connection = C3poDataSource.getConnection();
            PreparedStatement prepStatement = connection.prepareStatement("Select * from carros ");
            ResultSet resultSet = prepStatement.executeQuery();
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
         
            System.out.println("Sucesso ao buscar carros.");
            System.out.println(carros);
            return carros;
            
        } catch (SQLException ex) {
            System.out.print("Erro na listagem: " + ex.getMessage());
            return new ArrayList<Carro>();
        }finally {
            try { if (connection != null) connection.close(); } catch (Exception e) {};
        }
    }
	
    public int excluir(int id) {
    	Connection connection = null;
    	PreparedStatement prepStatement = null;
		try {
			connection = C3poDataSource.getConnection();
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
			connection = C3poDataSource.getConnection();
    		
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
			connection = C3poDataSource.getConnection();
			
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