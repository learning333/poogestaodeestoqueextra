package modelo;

public class Pessoa {

	private static int ultimoId = 0;

	private int id;
	private String nome;
	private String telefone;
	private String email;
	private String rua;
	private String numero;
	private String bairro;

	public Pessoa(String nome) {
		this.nome = nome;
		this.id = ++ultimoId;
	}

	public int getUltimoId() {
		return ultimoId;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome){
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone){
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}
	public void setRua(String entrada){
		this.rua = entrada;
	}
	public String getRua() {
		return rua;
	}
	public void setNumero(String entrada){
		this.numero = entrada;
	}
	public String getNumero() {
		return numero;
	}
	public void setBairro(String entrada){
		this.bairro = entrada;
	}
	public String getBairro() {
		return bairro;
	}

	public String toString() {
		return  "Id:" + this.id + "\n" +
				"Nome:" + this.nome + "\n" +
				"Telefone:" + this.telefone + "\n" +
				"Email:" + this.email + "\n"+
				"Endereco: "+ this.rua +", "+this.numero +" - " +this.bairro;
		

	}

}