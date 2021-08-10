package poogestaodeestoqueextra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import controle.ControleEstoque;
import modelo.LoteCompra;
import modelo.Produto;

public class TesteControleLotesCompra {
	ControleEstoque novoEstoque;
	Produto produtoTeste;	
	LoteCompra loteTeste;

	@Before
	public void inicializaEstoque(){
		novoEstoque = new ControleEstoque();

	}
	@Test
	public void criaVazios() {
		assertTrue(novoEstoque.getProdutos().isEmpty());
		assertTrue(novoEstoque.getLotes().isEmpty());


	}
	public void AuxAdicionaProdutoEFazCompra() {
		produtoTeste=novoEstoque.adicionarNovoProduto("produto Teste", "descricao teste");
		loteTeste=novoEstoque.adicionarNovoLote("refencia teste", "transito", produtoTeste, 10, (float) 1.5);
	}
	
	
	@Test
	public void adicionaProdutoEFazUmaCompra() {
		AuxAdicionaProdutoEFazCompra();
		
		assertFalse(novoEstoque.getLotes().isEmpty());
		assertEquals(loteTeste.getQtd(),10);
		assertEquals(loteTeste.getValor(),(float) 15.0, 0.01);
	}
	@Test
	public void buscaLotePorId() {
		assertTrue(novoEstoque.getLotes().isEmpty());
		AuxAdicionaProdutoEFazCompra(); //cadastra lotecompra com id=2
		System.out.print(loteTeste);
		assertEquals(novoEstoque.buscaLotePeloId(loteTeste.getId()).getNomeProduto(),"produto Teste");
		assertEquals(novoEstoque.buscaLotePeloId(loteTeste.getId()).getQtd(),10);
		assertEquals(novoEstoque.buscaLotePeloId(loteTeste.getId()).getValorEmMaos(),15.0f,0.01);
	}
	
	
	@Test
	public void recebeLote() {
		AuxAdicionaProdutoEFazCompra();
		
		
		
		assertEquals("transito",loteTeste.getStatus());
		novoEstoque.recebeLote(loteTeste);
		assertEquals("em maos",loteTeste.getStatus());
	}
	
	@Test
	public void encerraLote() {
		AuxAdicionaProdutoEFazCompra();
		novoEstoque.encerraLote(loteTeste);
		assertEquals("encerrado",loteTeste.getStatus());
		
	}
	
	@Test
	public void reativaLoteEncerrado() {
		recebeLote();//cria produto, lote e faz o recebimento
		loteTeste.setStatus("encerrado");
		novoEstoque.reativaLote(loteTeste, 0);
		assertEquals("em maos",loteTeste.getStatus());
	}
	

	
	
}
