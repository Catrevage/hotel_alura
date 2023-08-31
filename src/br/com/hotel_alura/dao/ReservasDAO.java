package br.com.hotel_alura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
				pstm.setString(4, reserva.getFormaPagamento().toUpperCase());

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

	public List<Reservas> listar() {
		try {

			List<Reservas> listaDeReservas = new ArrayList<Reservas>();
			String sql = "SELECT * FROM RESERVAS";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {
						Reservas reserva = new Reservas(rst.getInt(1), rst.getString(2), rst.getString(3),
								rst.getDouble(4), rst.getString(5));

						listaDeReservas.add(reserva);

					}

				}
			}

			return listaDeReservas;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reservas> buscaByID(String value) {
		int id = Integer.parseInt(value);
		List<Reservas> listaDeReservas = new ArrayList<Reservas>();
		try {

			
			String sql = "SELECT * FROM RESERVAS WHERE ID = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setInt(1, id);
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {
						Reservas reserva = new Reservas(rst.getInt(1), rst.getString(2), rst.getString(3),
								rst.getDouble(4), rst.getString(5));

						listaDeReservas.add(reserva);

					}

				}
			}

			return listaDeReservas;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deletar(int id) {
		try {
			String sql = "DELETE FROM RESERVAS WHERE ID = ?";
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setInt(1, id);
				pstm.execute();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
	}

	public void update(String dataEntrada, String dataSaida, Double valor, String pagamento, Integer id) {
		try {
			String sql = "UPDATE RESERVAS SET DATAENTRADA = ?, DATASAIDA = ?, VALOR = ?, FORMAPAGAMENTO = ? WHERE ID = ?";
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, dataEntrada);
				pstm.setString(2, dataSaida);
				pstm.setDouble(3, valor);
				pstm.setString(4, pagamento);
				pstm.setInt(5, id);
				pstm.execute();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
		
	}

}
