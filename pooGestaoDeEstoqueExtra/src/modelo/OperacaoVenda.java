package modelo;

public class OperacaoVenda extends OperacaoFinanceira{
	private Venda venda;
	
	public OperacaoVenda(Venda venda) {
		this.venda=venda;
	}

	public void setValor(float valor) {
		this.valor=valor;
		
	}
}
