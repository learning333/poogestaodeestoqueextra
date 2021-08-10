package poogestaodeestoqueextra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controle.ControleEstoque;
import modelo.Pessoa;
import modelo.Produto;

public class TesteControleProdutos {
	ControleEstoque novoEstoque;
	
	@Before
	public void inicializaEstoque(){
		novoEstoque = new ControleEstoque();

	}

	@Test
	public void criaVazios() {
		assertTrue(novoEstoque.getLivroCaixa().isEmpty());
		assertTrue(novoEstoque.getProdutos().isEmpty());
		assertTrue(novoEstoque.getLotes().isEmpty());
		assertTrue(novoEstoque.getVendas().isEmpty());

	}
	
	@Test
	public void adicionaProduto(){
		novoEstoque.adicionarNovoProduto("produto Teste", "descricao teste");
		assertFalse(novoEstoque.getProdutos().isEmpty());
		
		
		Produto produtoTeste=novoEstoque.BuscarProdutoPeloNome("produto Teste");
		
		assertEquals(produtoTeste.getNome(),"produto Teste");
		assertEquals(produtoTeste.getDescricao(),"descricao teste");
		
		//;
		//assertEquals("Acelerou 3 vez", 72,c.getVelocidade());

	}


	@Test
	public void listar_contatos() {
		novoEstoque.adicionarNovoProduto("produto Teste", "descricao teste");
		novoEstoque.adicionarNovoProduto("produto Teste2", "descricao teste2");

		
		ArrayList<Produto> manual=new ArrayList<Produto>();
		Produto produtoManual1=new Produto("produto Teste", "descricao teste");
		Produto produtoManual2=new Produto("produto Teste2", "descricao teste2");
		manual.add(produtoManual1);

		manual.add(produtoManual2);
		
		for(int i=0;i<manual.size();i++) {
			assertEquals(manual.get(i).getNome(),((ArrayList<Produto>) novoEstoque.visualizarProdutos()).get(i).getNome());
			assertEquals(manual.get(i).getDescricao(),((ArrayList<Produto>) novoEstoque.visualizarProdutos()).get(i).getDescricao());
		}
		
	}



}
