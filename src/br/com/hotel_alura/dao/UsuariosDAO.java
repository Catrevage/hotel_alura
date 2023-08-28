package br.com.hotel_alura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.hotel_alura.model.Usuarios;

public class UsuariosDAO {
	private Connection connection;
	
	public UsuariosDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void salvar(Usuarios usuario) throws SQLException {
		String sql = "INSERT INTO USUARIOS(NOME, SENHA) VALUES (?, ?)";

		try (PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stm.setString(1, usuario.getNome());
			stm.setString(2, usuario.getSenha());

			stm.execute();

			try (ResultSet result = stm.getGeneratedKeys()) {

				while (result.next()) {
					usuario.setId(result.getInt(1));
				}

			}
		}

	}
	
	public List<Usuarios> listar() throws SQLException {
		List<Usuarios> ListaDeUsuarios = new ArrayList<Usuarios>();
		
		String sql = "SELECT * FROM USUARIOS";

		try (PreparedStatement stm = connection.prepareStatement(sql)) {

			stm.execute();

			try (ResultSet result = stm.getResultSet()) {
				while (result.next()) {
					Usuarios usuario = new Usuarios(
							result.getInt(1), 
							result.getString(2), 
							result.getString(3));
					ListaDeUsuarios.add(usuario);
				}

			}
		}
		return ListaDeUsuarios;
	}
	
	

}
