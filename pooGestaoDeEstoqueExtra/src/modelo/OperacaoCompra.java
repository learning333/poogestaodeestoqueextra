package modelo;

public class OperacaoCompra extends OperacaoFinanceira{
	
	public OperacaoCompra(LoteCompra lote) {
		this.lote=lote;
	}

	private LoteCompra lote;

	public void setValor(float valor) {
		this.valor=-valor;
		
	}

	
}
