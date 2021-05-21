package com.edu.ufps.CrudJsp.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.edu.ufps.CrudJsp.model.Usuario;
import com.edu.ufps.CrudJsp.util.Conexion;

public class UsuarioDAO {

	private Conexion conexion;
	
	public static final String INSERT_USUARIO_SQL= "INSERT INTO usuario (nombre,email,pais) VALUES (?,?,?)";
	public static final String DELETE_USUARIO_SQL= "DELETE FROM usuario where id=?";
	public static final String UPDATE_USUARIO_SQL= "UPDATE usuario set nombre=?,email=?,pais=? where id=?";
	public static final String SELECT_ALL_USUARIO_SQL= "SELECT id,nombre,email,pais FROM usuario";
	public static final String SELECT_USUARIO_BY_ID="SELECT id,nombre,email,pais FROM usuario where id=?";
	
	public UsuarioDAO() {
		conexion=Conexion.getSingletonConexion();
	}
	
	
	public void insert(Usuario usuarioDTO ) {
		
		try {
			PreparedStatement prStm= this.conexion.getGestorConexion().prepareStatement(INSERT_USUARIO_SQL);
			prStm.setString(1,usuarioDTO.getNombre());
			prStm.setString(2,usuarioDTO.getEmail());
			prStm.setString(3,usuarioDTO.getPais());
			prStm.execute();
			System.out.println(prStm.getMaxRows());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void edit(Usuario usuarioDTO) {
		try {
			PreparedStatement prStm = this.conexion.getGestorConexion().prepareStatement(UPDATE_USUARIO_SQL);		
			prStm.setString(1,usuarioDTO.getNombre());
			prStm.setString(2,usuarioDTO.getEmail());
			prStm.setString(3,usuarioDTO.getPais());
			prStm.setInt(4, usuarioDTO.getId());
			prStm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void delete(Usuario usuarioDTO) {
		try {
			PreparedStatement prStm = this.conexion.getGestorConexion().prepareStatement(DELETE_USUARIO_SQL);		
			
			prStm.setInt(1, usuarioDTO.getId());
			prStm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Usuario> listarTodo (){
		List<Usuario>usuarios=new ArrayList<>();
		try {
			PreparedStatement prStm= this.conexion.getGestorConexion().prepareStatement(SELECT_ALL_USUARIO_SQL);
			prStm.execute();
			ResultSet rs= prStm.getResultSet();			
			while(rs.next()) {
				usuarios.add(new Usuario(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuarios;
	}
	
	public Usuario usuarioActual(Usuario usuarioDTO) {
		Usuario usuarioActual=null;
		
		try {
			PreparedStatement prStm= this.conexion.getGestorConexion().prepareStatement(SELECT_USUARIO_BY_ID);
			prStm.setInt(1, usuarioDTO.getId());
			prStm.execute();
			ResultSet rs= prStm.getResultSet();
			if(rs.next()) {
				usuarioActual=new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return usuarioActual;
	}
}
