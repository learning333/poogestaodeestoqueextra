package modelo;






public class Venda {

	private static int ultimoId = 0;
	
	protected int id;
	
	public String nomeCliente;
	
	public LoteCompra lote;

	public String status;
	
	public float precoVenda;
	
	public int qtd;
	
	public float lucro;

	
	public Venda(String nomeCliente, float precoVenda, LoteCompra lote, int qtd,float lucro) {
		super();
		this.id = ++ultimoId;
		this.nomeCliente = nomeCliente;
		this.precoVenda = precoVenda;
		this.lote=lote;
		this.qtd=qtd;
		this.lucro=lucro;
	}

	public void setStatus(String string) {
		this.status=string;
		
	}
	//public long getLoteID() {
	//	return this.lote.getId();
	//}
	public int getQtd() {
		return this.qtd;
	}


	
	public String toString() {
		return " ID VENDA: ["+id+"] Cliente=" + nomeCliente + " Status=" + status + 
				"\n Produto ["+lote.getNomeProduto()+"] Qtd: "+qtd+ " Valor Un: "+precoVenda+
				"\n Valor Total: "+qtd*precoVenda +" Lucro: "+lucro;
	}

	public LoteCompra getLote() {
		return this.lote;
	}

	public String getStatus() {
		return status;
	}

	public int getId() {
		
		return id;
	}

	public float getPrecoTotal() {
		
		return qtd*precoVenda;
	}


	
}

