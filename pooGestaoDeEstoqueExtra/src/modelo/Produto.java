package modelo;

import java.util.List;




public class Produto{
	private static int ultimoId = 0;

	protected int id;
	

	protected String nome;
		

	protected String descricao;
	

	private List<LoteCompra> lotes;
	
	
	public Produto(String nome, String descricao) {
		super();
		this.id = ++ultimoId;
		this.nome=nome;
		this.descricao=descricao;
	}



	public String toString() {
		return "ID: [" + id + "] Nome=" + nome + " Descricao=" + descricao;//, lotes=" + lotes + "]";
	}
	
	public String getNome() {
		return this.nome;
	}



	public Object getDescricao() {

		return this.descricao;
	}
	
}
