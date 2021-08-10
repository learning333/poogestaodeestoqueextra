package poogestaodeestoqueextra;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import controle.ControleEstoque;
import modelo.LoteCompra;
import modelo.Produto;
import modelo.Venda;

public class TesteControleVendas {
	ControleEstoque novoEstoque;
	Produto produtoTeste;
	LoteCompra loteTeste;
	Venda venda;
	int qtd;
	float pv;
	float lucro;
	
	@Before
	public void inicializaEstoque(){
		novoEstoque = new ControleEstoque();

	}
	@Test
	public void criaVazios() {
		assertTrue(novoEstoque.getVendas().isEmpty());
		assertTrue(novoEstoque.getLotes().isEmpty());
	}
	public void passosPreVenda() {
		produtoTeste=novoEstoque.adicionarNovoProduto("produto Teste", "descricao teste");
		loteTeste=novoEstoque.adicionarNovoLote("refencia teste", "transito", produtoTeste, 10, (float) 1.5);
		novoEstoque.recebeLote(loteTeste);
	}
	public void passosVenda(int qtd, float pv) {
		passosPreVenda();
		float lucro=pv-loteTeste.getCusto();
		venda=novoEstoque.adicionarNovaVenda("cliente Teste", pv, loteTeste, qtd, lucro);
	}
	
	@Test
	public void novaVenda() {
		qtd=2;
		pv=15.0f;
		passosVenda(qtd,pv);
	
		assertFalse(novoEstoque.getVendas().isEmpty());
		
		assertEquals(loteTeste,venda.getLote());
		assertEquals(qtd,venda.getQtd());
		assertEquals("normal",venda.getStatus());
		assertEquals(qtd*pv,venda.getPrecoTotal(),0.01);

		Venda vendaManual=new Venda("Fulano",15.0f, loteTeste, 2,15.0f-loteTeste.getCusto());
		vendaManual.setStatus("normal");
		assertEquals(vendaManual.getLote(),venda.getLote());
		assertEquals(vendaManual.getPrecoTotal(),venda.getPrecoTotal(),0.01);
		assertEquals(vendaManual.getQtd(),venda.getQtd());
		assertEquals(vendaManual.getStatus(),venda.getStatus());
		
	}
	@Test
	public void buscaVendaPorId() {
		qtd=2;
		pv=15.0f;
		passosVenda(qtd,pv);//cadastra venda com id1
		Venda vendaBuscada=novoEstoque.buscaVendaPeloId(1);
		assertEquals(2,vendaBuscada.getQtd());
		assertEquals(30.0,vendaBuscada.getPrecoTotal(),0.01);
		assertEquals("normal",vendaBuscada.getStatus());
		assertEquals(1,vendaBuscada.getId());
		//System.out.print(venda);
	}
	@Test
	public void devolucaoVenda() {
		passosPreVenda();//cria e recebe um pedido de compra de quantidade 10 e custo un 1.50
		assertEquals(10,loteTeste.getQtd());

		Venda venda1=novoEstoque.adicionarNovaVenda("homer", 3.0f, loteTeste, 6, 1.5f);
		Venda venda2=novoEstoque.adicionarNovaVenda("bart", 3.0f, loteTeste, 4, 1.5f);
		
		
		assertEquals(10,loteTeste.getQtdVendida());
		assertEquals("encerrado",loteTeste.getStatus());
		
		Venda vendaDevolvida=novoEstoque.entradaDevolucao(venda1);//devolve venda de quantidade 6
		assertEquals("devolvido",vendaDevolvida.getStatus());
		assertEquals(4,loteTeste.getQtdVendida());
		assertEquals("em maos",loteTeste.getStatus());
	}
}
