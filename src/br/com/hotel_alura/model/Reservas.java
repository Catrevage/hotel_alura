package br.com.hotel_alura.model;

public class Reservas {
	public int id;
	public String dataEntrada;
	public String dataSaida;
	public double valor;
	public String formaPagamento;
	
	public Reservas(String date, String date2, double valor, String formaPagamento) {
		this.dataEntrada = date;
		this.dataSaida = date2;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
	}
	
	public Reservas(int id, String dataEntrada, String dataSaida, double valor, String formaPagamento) {
		this.id = id;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	
	
	
	
	

}
