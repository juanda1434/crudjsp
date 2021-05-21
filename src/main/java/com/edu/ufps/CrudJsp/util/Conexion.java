package com.edu.ufps.CrudJsp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	
	private Connection gestorConexion;
	private static Conexion singletonConexion;
	private static final String url = "jdbc:mysql://localhost:3306/";
	private static final String dbName="test";
	private static final String driver= "com.mysql.cj.jdbc.Driver";
	private static final String user= "root";
	private static final String pass= "";
	
	public Conexion() {
		try {
			Class.forName(driver).newInstance();
			gestorConexion = DriverManager.getConnection(url+dbName,user,pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static Conexion getSingletonConexion() {
		return singletonConexion==null ? singletonConexion=new Conexion():singletonConexion;
	}
	
	public void cerrarConexion() {
		try {
			gestorConexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getGestorConexion() {
		return gestorConexion;
	}
}


