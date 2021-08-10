package poogestaodeestoqueextra;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import controle.ControleEstoque;
import modelo.LoteCompra;
import modelo.Produto;
import modelo.Venda;

public class TesteRelatorios {
	ControleEstoque novoEstoque;
	Produto produtoTeste;	
	LoteCompra loteTeste;
	Venda venda;
	
	@Before
	public void inicializaEstoque(){
		novoEstoque = new ControleEstoque();

	}
	@Test
	public void somatoriaDeCompras() {
		produtoTeste=novoEstoque.adicionarNovoProduto("produto Teste", "descricao teste");
		LoteCompra loteTeste1=novoEstoque.adicionarNovoLote("pedido 1", "transito", produtoTeste, 10, (float) 1.5);
		LoteCompra loteTeste2=novoEstoque.adicionarNovoLote("pedido 2", "transito", produtoTeste, 10, (float) 3.0);
		
		assertEquals(45.0,novoEstoque.somaLotes(),0.01);
		assertEquals("2 Lotes com status [transito] Valor Total: 45.0",novoEstoque.resumoLote("transito"));
	}
	
	@Test
	public void somatoriaVendasNaoDev() {
		produtoTeste=novoEstoque.adicionarNovoProduto("produto Teste", "descricao teste");
		//caixa-15
		LoteCompra loteTeste1=novoEstoque.adicionarNovoLote("pedido 1", "transito", produtoTeste, 10, (float) 1.5);
		
		//caixa -15+200=185
		Venda venda1=novoEstoque.adicionarNovaVenda("cliente azul", 200.0f, loteTeste1, 1, 200.0f-1.5f);
		
		//caixa 185+400=585
		Venda venda2=novoEstoque.adicionarNovaVenda("cliente verde", 200.0f, loteTeste1, 2, 400.0f-3.0f);
		
		//caixa 585+400=985
		Venda venda3=novoEstoque.adicionarNovaVenda("cliente devolvedor", 200.0f, loteTeste1, 2, 400.0f-3.0f);
		
		//caixa 985-400=585
		novoEstoque.entradaDevolucao(venda3);
		
		assertEquals(600.0,novoEstoque.somaVendasNaoDevolvidas(),0.01);
		assertEquals("Saldo: 585.0",novoEstoque.resumoCaixa());
	}

}
