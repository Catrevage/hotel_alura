package br.com.hotel_alura.model;

public class Hospedes {
	private int id;
	private String nome;
	private String sobreNome;
	private String dataNascimento;
	private String nacionalidade;
	private String telefone;
	private int idReserva;
	 
	public Hospedes(String nome, String sobreNome,String dataNascimento, String nacionalidade, String telefone,
			int idReserva) {
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.dataNascimento = dataNascimento;
		this.nacionalidade = nacionalidade;
		this.telefone =telefone;
		this.idReserva = idReserva;
		
	}
	
	public Hospedes(int id, String nome, String sobreNome, String dataNascimento, String nacionalidade, String telefone,
			int idReserva) {
		this.id = id;
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.dataNascimento = dataNascimento;
		this.nacionalidade = nacionalidade;
		this.telefone =telefone;
		this.idReserva = idReserva;
		
	}

	public int getId() {
		return id;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	
	
}
