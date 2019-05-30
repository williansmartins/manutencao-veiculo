package com.williansmartins.manutencaoveiculo.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3poDataSource {
 
    private static ComboPooledDataSource cpds = new ComboPooledDataSource();
//	private final static String driver          = "com.mysql.jdbc.Driver";
//	private final static String url             = "jdbc:mysql://pwms.com.br:3306/waisoc_manutencao";
//	private final static String user            = "waisoc_manutenca";
//	private final static String pwd             = "manutencao123";

	private final static String driver          = "com.mysql.jdbc.Driver";
	private final static String url             = "jdbc:mysql://localhost:3306/manutencao_veiculo";
	private final static String user            = "root";
	private final static String pwd             = "";
    
 
    static {
        try {
            cpds.setDriverClass(driver);
            cpds.setJdbcUrl(url);
            cpds.setUser(user);
            cpds.setPassword(pwd);
            
            cpds.setMaxPoolSize(4);
            cpds.setMinPoolSize(1);
        } catch (PropertyVetoException e) {
            // handle the exception
        	System.out.println(">>>" + e.getMessage());
        	e.printStackTrace();
        }
    }
     
    public static Connection getConnection() throws SQLException {
        return cpds.getConnection();
    	//return getGenericConnection();
    }
     
    private C3poDataSource(){}
    
    public static Connection getGenericConnection() {

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
}