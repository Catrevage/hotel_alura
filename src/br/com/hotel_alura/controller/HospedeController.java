package br.com.hotel_alura.controller;

import java.sql.Connection;
import java.util.List;

import br.com.hotel_alura.dao.HospedesDAO;
import br.com.hotel_alura.factory.ConnectionFactory;
import br.com.hotel_alura.model.Hospedes;

public class HospedeController {
	private HospedesDAO hospedesDAO;
	
	public HospedeController() {
		Connection connection = new ConnectionFactory().criaConexao();
		this.hospedesDAO = new HospedesDAO(connection);
	}
	
	public int insere(Hospedes hospede) {
		hospedesDAO.salvarComReserva(hospede);
		return hospede.getId();
	}
	
	public List<Hospedes> lista(){
		return hospedesDAO.listar();		
	}
	
	public List<Hospedes> busca(String sNome){
		return hospedesDAO.buscaSobrenome(sNome);		
	}
	
	public void deletaByIdReserva(int id_reserva) {
		hospedesDAO.deletaByreserva(id_reserva);
	}

	public void alterar(String nome, String sobreNome, String dataNascimento, String nacionalidade, String telefone,
			Integer id) {
		hospedesDAO.update(nome, sobreNome, dataNascimento, nacionalidade, telefone, id);
		
	}

	
}
