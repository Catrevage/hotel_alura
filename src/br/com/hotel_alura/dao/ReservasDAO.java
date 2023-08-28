package br.com.hotel_alura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import br.com.hotel_alura.model.Reservas;

public class ReservasDAO {
	private Connection connection;

	public ReservasDAO(Connection connection) {
		this.connection = connection;
	}

	public int salvar(Reservas reserva) {
		try {

			String sql = "INSERT INTO RESERVAS " + "(DATAENTRADA, DATASAIDA, VALOR, FORMAPAGAMENTO) "
					+ "VALUES(?,?,?,?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setString(1, reserva.getDataEntrada());
				pstm.setString(2, reserva.getDataSaida());
				pstm.setDouble(3, reserva.getValor());
				pstm.setString(4, reserva.getFormaPagamento());

				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						reserva.setId(rst.getInt(1));
					}
				}
			}
			return reserva.getId();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
	}

	public List<Reservas> listar(){
		try {
			
			List<Reservas> listaDeReservas = new ArrayList<Reservas>();
			String sql = "SELECT * FROM RESERVAS";
			
			try(PreparedStatement pstm = connection.prepareStatement(sql, 
					Statement.RETURN_GENERATED_KEYS)){
				pstm.execute();
				
				try(ResultSet rst = pstm.getGeneratedKeys()){
					Reservas reserva = new Reservas(rst.getInt(1), 
							rst.getString(2),
							rst.getString(3),
							rst.getDouble(4),
							rst.getString(5));
					listaDeReservas.add(reserva);
				}
			}
			
			return listaDeReservas;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deletar(int id) {
		try {
			String sql = "DELETE * FROM RESERVAS WHERE ID = ?";
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setInt(1, id);
				pstm.execute();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
	}

}
