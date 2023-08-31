package br.com.hotel_alura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.hotel_alura.model.Hospedes;

public class HospedesDAO {
	private Connection connection;

	public HospedesDAO(Connection connection) {
		this.connection = connection;
	}

	public int salvarComReserva(Hospedes hospede) {
		try {

			String sql = "INSERT INTO HOSPEDES "
					+ "(NOME, SOBRENOME, DATANASCIMENTO, NACIONALIDADE, TELEFONE, ID_RESERVA) " + "VALUES(?,?,?,?,?,?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setString(1, hospede.getNome().toUpperCase().trim());
				pstm.setString(2, hospede.getSobreNome().toUpperCase().trim());
				pstm.setString(3, hospede.getDataNascimento());
				pstm.setString(4, hospede.getNacionalidade().toUpperCase());
				pstm.setString(5, hospede.getTelefone().trim());
				pstm.setInt(6, hospede.getIdReserva());

				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						hospede.setId(rst.getInt(1));
					}
				}
			}
			return hospede.getId();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
	}

	public List<Hospedes> listar() {
		try {

			List<Hospedes> listaDeHospedes = new ArrayList<Hospedes>();
			String sql = "SELECT * FROM HOSPEDES";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {

					while (rst.next()) {

						Hospedes hospede = new Hospedes(rst.getInt(1), rst.getString(2), rst.getString(3),
								rst.getString(4), rst.getString(5), rst.getString(6), rst.getInt(7));

						listaDeHospedes.add(hospede);
					}

				}
			}

			return listaDeHospedes;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Hospedes> buscaSobrenome(String sNome) {
		List<Hospedes> listaDeHospede = new ArrayList<Hospedes>();
		try {

			String sql = "SELECT * FROM HOSPEDES WHERE SOBRENOME = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, sNome);
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {
						Hospedes hospede = new Hospedes(rst.getInt(1), rst.getString(2), rst.getString(3),
								rst.getString(4), rst.getString(5), rst.getString(6), rst.getInt(7));

						listaDeHospede.add(hospede);
					}
				}
			}
			return listaDeHospede;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public void deletaByreserva(int id_reserva) {
		String sql = "DELETE FROM HOSPEDES WHERE ID_RESERVA = ?";
		try {
			try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				pstm.setInt(1, id_reserva);
				pstm.execute();
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);

		}
	}

	public void update(String nome, String sobreNome, String dataNascimento, String nacionalidade, String telefone,
			Integer id) {
		try {
			String sql = "UPDATE HOSPEDES SET NOME = ?, SOBRENOME = ?, DATANASCIMENTO = ?, NACIONALIDADE = ?,  TELEFONE = ? WHERE ID = ?";
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, nome);
				pstm.setString(2, sobreNome);
				pstm.setString(3, dataNascimento);
				pstm.setString(4, nacionalidade);
				pstm.setString(5, telefone);
				pstm.setInt(6, id);
				pstm.execute();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
		
	}
}
