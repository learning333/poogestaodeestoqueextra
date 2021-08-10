package controle;

import java.util.ArrayList;

import modelo.LoteCompra;
import modelo.OperacaoCompra;
import modelo.OperacaoFinanceira;
import modelo.OperacaoVenda;
import modelo.Produto;
import modelo.Venda;

public class ControleEstoque {
	private ArrayList<OperacaoFinanceira> livroCaixa;
	private ArrayList<LoteCompra> lotes;
	private ArrayList<Venda> vendas;
	private ArrayList<Produto> produtos;
	
	
	public ControleEstoque() {
		this.livroCaixa = new  ArrayList<OperacaoFinanceira>();
		this.lotes =  new ArrayList<LoteCompra>();
		this.vendas = new  ArrayList<Venda>();
		this.produtos = new  ArrayList<Produto>();
	}
	
	//---------------------------------------------------------------------------produto
	public Produto adicionarNovoProduto(String nome, String descricao) {
		Produto produto=new Produto(nome, descricao);
		this.produtos.add(produto);
		return produto;
	}
	
	public Iterable<Produto> visualizarProdutos(){
		return this.produtos;
	}
	public  Produto BuscarProdutoPeloNome(String nome) {
		Produto result=null;
		for(int i =0; i<this.produtos.size(); i++){
			
			String nome2=this.produtos.get(i).getNome();
			
			if(nome2.equals(nome)){

				System.out.println("Produto Encontrado!");
				System.out.println("");
				result=this.produtos.get(i);
				
			}
		}
		return result;
	}
	
	//---------------------------------------------------------------------------loteCompra
	
	public LoteCompra adicionarNovoLote(String referencia,String status,Produto produto,int qtd,float precoUn) {
		LoteCompra novoLote= new LoteCompra(referencia,status,produto,qtd,precoUn);
		this.lotes.add(novoLote);
		novaOpCompra(novoLote);
		return novoLote;
	}
	private void novaOpCompra(LoteCompra lote) {
		OperacaoCompra nova= new OperacaoCompra(lote);
		nova.setValor(lote.getQtd()*lote.getCusto());
		this.livroCaixa.add(nova);

	}
	
	
	public Iterable<LoteCompra> listarLotes(){
		return this.lotes;
	}
	public LoteCompra buscaLotePeloId(int id){
		LoteCompra result=null;
		for(int i =0; i<this.lotes.size(); i++){
			
			int id2=this.lotes.get(i).getId();
			
			if(id2==id){

				result=this.lotes.get(i);
				
			}
		}
		return result;
	}
	
	public LoteCompra recebeLote(LoteCompra lote) {
		lote.setStatus("em maos");
		return lote;
	}
	public LoteCompra reativaLote(LoteCompra lote,int qtd) {
		lote.setQtdVendida(-qtd);	//qtdVendida+=qtd
		lote.setStatus("em maos");
		return lote;
	}
	public LoteCompra encerraLote(LoteCompra lote) {
		lote.setStatus("encerrado");
		return lote;
	}
	
	//---------------------------------------------------------------------------Venda
	
	public Venda buscaVendaPeloId(int id){
		Venda result=null;
		for(int i =0; i<this.vendas.size(); i++){
			
			int id2=this.vendas.get(i).getId();
			
			if(id2==id){

				result=this.vendas.get(i);
				
			}
		}
		return result;
	}

	public Venda adicionarNovaVenda(String nome, float precoVenda, LoteCompra lote, int qtd, float lucro) {
		Venda novo=new Venda(nome,precoVenda,lote,qtd,lucro);
		lote.setQtdVendida(qtd);//update qtd vendida
		//lote.setQtdVendida(qtd);//qtdvendida+=qtd
		if(lote.getQtd()==lote.getQtdVendida()) {
			encerraLote(lote);//vendeu toda quantidade do lote de compra
		}
		
		novo.setStatus("normal");
		this.vendas.add(novo);
		novaOpVenda(novo);
		return novo;
	}
	
	
	
	private void novaOpVenda(Venda venda) {
		OperacaoVenda nova= new OperacaoVenda(venda);
		nova.setValor(venda.getPrecoTotal());
		this.livroCaixa.add(nova);

	}
	
	
	public Iterable<Venda> listarVendas(){

		return this.vendas;
	}
	public Venda entradaDevolucao(Venda venda) {
		venda.setStatus("devolvido");//update
		devolveQtdLote(venda);
		OpVendaRecebimento(venda);
		return venda;
	}
	//devolver quantidade para o lotecompra
	private void devolveQtdLote(Venda venda) {
		LoteCompra lotecompra=venda.getLote();
		
		if(lotecompra.getStatus().equals("encerrado")) {//se estava zerado reativa o lote compra
			lotecompra=reativaLote(lotecompra, venda.getQtd());
		}else {//se nao estava zerado "devolve" quantidade da venda
			lotecompra.setQtdVendida(-venda.getQtd());
		}
	}
	
	private void OpVendaRecebimento(Venda venda) {
		OperacaoVenda nova= new OperacaoVenda(venda);
		nova.setValor(-venda.getPrecoTotal());
		this.livroCaixa.add(nova);

	}
	
	//-----------------------------------------------------------Relatorios
	public float somaLotes() {
		Iterable<LoteCompra> lista =listarLotes();
		float valor=0;
		for(LoteCompra lote: lista) {
			valor+=lote.getValor();
		}
		return valor;
	}
	public String resumoLote(String status) {
		int conta=0;
		float valor=0;

		
		Iterable<LoteCompra> lista =listarLotes();
		for(LoteCompra lote: lista) {
			if(lote.getStatus().equals(status)){
				conta+=1;
				if(status.equals("transito")) {
					valor+=lote.getValor();
				}else {
					valor+=lote.getValorEmMaos();
				}
			}
		}
		return conta+" Lotes com status ["+status+ "] Valor Total: "+valor;
	}

	public float somaVendasNaoDevolvidas() {
		float valorVendido=0;	
		for(int i =0; i<this.vendas.size(); i++){
			if(this.vendas.get(i).getStatus().equals("normal")){

				valorVendido+=this.vendas.get(i).getPrecoTotal();
				
			}
		}
		return valorVendido;
	}
	
	public String resumoVendas() {
		float valorVendido=somaVendasNaoDevolvidas();
		return "Valor vendido: " +valorVendido;
	}

	public String resumoCaixa() {
		float valorVendido=somaVendasNaoDevolvidas();
		float valorComprado=somaLotes();
		float saldo=valorVendido-valorComprado;
		return "Saldo: "+saldo;
	}

	public ArrayList<OperacaoFinanceira> getLivroCaixa() {
		return livroCaixa;
	}


	public ArrayList<LoteCompra> getLotes() {
		return lotes;
	}



	public ArrayList<Venda> getVendas() {
		return vendas;
	}



	public ArrayList<Produto> getProdutos() {
		return produtos;
	}


	
	
	
}
