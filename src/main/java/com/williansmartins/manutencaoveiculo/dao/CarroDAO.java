package com.williansmartins.manutencaoveiculo.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.williansmartins.manutencaoveiculo.model.Carro;

public class CarroDAO{

	final static String driver          = "com.mysql.jdbc.Driver";
    final static String url             = "jdbc:mysql://pwms.com.br:3306/waisoc_manutencao";
    final static String user            = "waisoc_manutenca";
    final static String pwd             = "manutencao123";

    public static Connection con            = null;
    public static Statement  statement;
    public static ResultSet  resultset;

    public void conecta() {

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);
            con.setAutoCommit(false);
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
                Statement st = con.createStatement();
                int id = st.executeUpdate(sql);
                con.commit();
                con.close();
                System.out.println("Inserido com sucesso");
                return id;

        } catch (SQLException ex) {
            System.out.print("Erro ao inserir: " + ex.getMessage());
            return 0;
        }
    }
 
    public List<Carro> listagem() {
        //Metodo para popular a tabela com os registros que estÃ£o no banco de dados
        try {
            conecta();
            java.sql.PreparedStatement stmtQuery = CarroDAO.con.prepareStatement("Select * from carros ");
            ResultSet resultSet = stmtQuery.executeQuery();
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
            con.close();
            return carros;
            
        } catch (SQLException ex) {
            System.out.print("Erro na listagem: " + ex.getMessage());
            return null;
        }
    }
	
    public void excluir(int id) {
	     try {
	        conecta();
        	String sql = "Delete from carros where id=" + id + "";
        	statement = (Statement) CarroDAO.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, resultset.CONCUR_READ_ONLY);
        	statement.executeUpdate(sql);
	        
	        con.commit();
	        System.out.print("Item excluido com sucesso!");
	        statement.close();
	        }catch (SQLException ex) {
	             System.out.println("Erro ao excluir: "+ex.getMessage());
	        }
	}
    
    public int atualizar(Carro carro) {
    	try {
    		conecta();
    		
    		String fabricanteSQL = "";
    		String modeloSQL = "";
    		String anoSQL = "";
    		String clausulas = "";
    		
    		if(carro.getFabricante() != null) {
    			fabricanteSQL = "fabricante = '"+ carro.getFabricante() +"',";
    		}
    		
    		if(carro.getModelo() != null) {
    			modeloSQL = "modelo = '"+ carro.getModelo() +"',";
    		}
    		
    		if(carro.getAno() != null) {
    			anoSQL = "ano = '"+ carro.getAno() +"'";
    		}
    		
    		String sql = "UPDATE carros SET " + fabricanteSQL + " " + modeloSQL + " " + anoSQL + " WHERE id=" + carro.getId() + "";
    		
    		sql = removerVirgula(sql);
    		
    		statement = (Statement) CarroDAO.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, resultset.CONCUR_READ_ONLY);
    		int resultado = statement.executeUpdate(sql);
    		
    		con.commit();
    		System.out.print("Item atualizado com sucesso!");
    		statement.close();
    		
    		return resultado;
    	} catch (SQLException ex) {
    		System.out.println("Erro ao atualizar: " + ex.getMessage());
    		return 0;
    	}
    }

	private String removerVirgula(String sql) {
		//sql = "UPDATE carros SET fabricante = 'Honda',  WHERE id=25";
		int posicaoWhere = sql.lastIndexOf("WHERE");
		System.out.println("posicaoWhere: " + posicaoWhere);
		int posicaoDaVirgula = sql.lastIndexOf(",", posicaoWhere);
		int diferenca = posicaoWhere - posicaoDaVirgula;

		if(diferenca <= 4) {
			String result = sql.substring(0, posicaoWhere-diferenca) + sql.substring(posicaoWhere-diferenca+1);
			return result;
		}else {
			return sql;
		}
	}

	public Carro buscarPorId(int id){

	    	try{
	    		conecta();
	    		Statement st = con.createStatement();
	    		String sql = "select * from carros where id = "+id+" ";
	    		ResultSet rs = st.executeQuery(sql); 

	    		rs.first();

	    		Carro carro = new Carro();

	    		carro.setId(rs.getInt("id"));
	    		carro.setFabricante(rs.getString("fabricante"));
	    		carro.setModelo(rs.getString("modelo"));
	    		carro.setAno(rs.getString("ano"));
	            
	            return carro;
	        }
	        catch (SQLException ex) {
	            System.out.print("Erro ao preparar: " + ex.getMessage());
	            return null;
	        }
	        
	        
	        
	   }
}