package br.com.hotel_alura.controller;

import java.sql.Connection;
import java.util.List;

import br.com.hotel_alura.dao.ReservasDAO;
import br.com.hotel_alura.factory.ConnectionFactory;
import br.com.hotel_alura.model.Reservas;

public class ReservaController {
	private ReservasDAO reservasDao;
	
	public ReservaController() {
		Connection connection = new ConnectionFactory().criaConexao();
		this.reservasDao = new ReservasDAO(connection);
	}
	
	public int insere(Reservas reserva) {
		reservasDao.salvar(reserva);
		return reserva.getId();
	}
	
	public void deleta(int id) {
		reservasDao.deletar(id);
	}
	
	public List<Reservas> lista(){
		return reservasDao.listar();
	}
	
	public List<Reservas> busca(String value){
		return reservasDao.buscaByID(value);
	}
	

}
