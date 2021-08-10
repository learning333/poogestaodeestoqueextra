package modelo;

import java.util.List;



public class LoteCompra {
	private static int ultimoId = 0;

	protected int id;
	

	protected String referencia;
	
	
	public String status;
	
	

	private List<Venda> ListaDeVendas;
	

	private Produto produto;
	
	private int qtd;
	
	private float custo;
	
	private int qtdVendida;
	
	
	
	
	public LoteCompra(String referencia, String status, Produto produto,int qtd, float custo) {
		super();
		this.id = ++ultimoId;
		this.referencia = referencia;
		this.status = status;
		this.produto = produto;
		this.qtd=qtd;
		this.custo=custo;
	}

	public void setStatus(String string) {
		this.status=string;
		
	}
	public String getStatus() {
		return status;		
	}

	public int getQtd() {
		return qtd;
	}

	public int getQtdVendida() {
		return qtdVendida;
	}


	public float getCusto() {
		return custo;
	}

	public void setQtdVendida(int i) {
		this.qtdVendida+=i;
		
	}

	@Override
	public String toString() {
		return " ID COMPRA: ["+id+"] Ref: " + referencia + 
				"\n Produto[ " + produto +
				"]\n Status: " + status + 
				"\n Quantidade: " + qtd + "   Custo Un: "+ custo + "   Custo Total: "+qtd*custo+
				"\n Quantidade Vendida: " + qtdVendida;
	}
	public String listagemParaVenda() {
		return " ID COMPRA: ["+id+"] Produto[ " + produto.getNome() +"] Quantidade Disponivel: "+(qtd-qtdVendida);
	}



	public String getNomeProduto(){
		return this.produto.getNome();
	}

	public int getId() {
		
		return id;
	}

	public float getValor() {
	
		return qtd*custo;
	}

	public float getValorEmMaos() {
		
		return (qtd-qtdVendida)*custo;
	}

	
	
}
