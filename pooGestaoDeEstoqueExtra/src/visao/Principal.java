package visao;


import java.util.Optional;
import java.util.Scanner;


import controle.ControleEstoque;
import modelo.LoteCompra;
import modelo.Produto;
import modelo.Venda;


public class Principal {
	private static ControleEstoque controle;


	public static void main(String[] args) {
		//this.visaoProd= 
		controle=new ControleEstoque();
		Boolean gatilho=true;
		Scanner scanner=new Scanner(System.in);
		
		while(gatilho) {
			System.out.println("Escolha entidade");
			System.out.println("-----------");
			System.out.println("1-Produtos");
			System.out.println("2-Compras");
			System.out.println("3-Vendas");
			System.out.println("4-Situacional");
			
			int opcao=scanner.nextInt();
			
			switch(opcao) {
			case 1:
				menuProdutos(scanner);
				
				break;
			case 2:
				menuLotes(scanner);
				
				break;
			case 3:
				menuVendas(scanner);
				
				break;
			case 4:
				menuRelatorios();

				break;
			default:
				gatilho=false;
			}
			
		}
	}

	public static void menuProdutos(Scanner scanner) {
		boolean gatilho=true;
		while(gatilho) {
			System.out.println("\nEscolha acao");
			System.out.println("0-voltar");
			System.out.println("1-cadastrar novo produto");
			System.out.println("2-listar produtos");
			
			int opcao=scanner.nextInt();
			
			switch(opcao) {
			case 1:
				cadastrarProduto(scanner);
				
				break;
			case 2:
				visualizarProdutos();
				break;
			default:
				gatilho=false;
			}
			
		}
		System.out.println();
	}
	private static void cadastrarProduto(Scanner scanner) {
		System.out.println("-------------Cadastrando novo produto-------------");
		System.out.print("Digite Nome: ");
		String nome=scanner.next();
	
		System.out.print("Digite a Descricao: ");
		String descricao=scanner.next();
		
		Produto novo=controle.adicionarNovoProduto(nome,descricao);
		System.out.println("Salvo!\n");
		System.out.println(novo);
		System.out.println("--------------------------------------------------");
	
	}
	
	private static void visualizarProdutos() {
		System.out.println("\n----------Listando Produtos Cadastrados-----------");
		Iterable<Produto> lista = controle.visualizarProdutos();
		for(Produto produto: lista) {
			
			System.out.println(produto);
		}
		System.out.println("-----------------------Fim------------------------");
		System.out.println();
	}
	
	public static void menuLotes(Scanner scanner) {
		boolean gatilho=true;
		while(gatilho) {
			System.out.println("\nEscolha acao");
			System.out.println("0-voltar");
			System.out.println("1-Nova compra");
			System.out.println("2-Listar lotes");
			System.out.println("3-Recebimento");

			
			int opcao=scanner.nextInt();
			
			switch(opcao) {
			case 1:
				cadastrarLote(scanner);
				break;
			case 2:
				visualizarLotes();
				break;
			case 3:
				recebimentoLote(scanner);
				break;
			default:
				gatilho=false;
			}
			
		}
		System.out.println();
	}
	private static void cadastrarLote(Scanner scanner) {

		System.out.println("-------------Cadastrando nova compra--------------");

		System.out.print("Digite referencia para o pedido: ");
		String referencia=scanner.next();
		
		//visualizar produtos disponiveis para compra
		System.out.println("\n-------Listando Produtos Cadastrados--------");
		Iterable<Produto> lista = controle.visualizarProdutos();
		for(Produto produto: lista) {
			
			System.out.println(produto);
		}
		System.out.println("--------------------Fim---------------------");
		System.out.println();
		//fim visualizacao
		
		System.out.print("Digite o nome do produto:");
		String nomebusca=scanner.next();
		
		Produto produto = controle.BuscarProdutoPeloNome(nomebusca);
		// produto esta cadastrado?
		
			System.out.print("Digite quantidade: ");
			int qtd=scanner.nextInt();
			System.out.print("Digite preco unitario");
			float precoUn=scanner.nextFloat();
			float valorTotal=qtd*precoUn;

		
			LoteCompra lote=controle.adicionarNovoLote(referencia, "transito", produto, qtd, precoUn);
			
			System.out.println("\nSALVO!\n");
			System.out.println(lote);
			System.out.println("--------------------------------------------------");
			
	}
	
	private static void visualizarLotes() {
		System.out.println("------------Listando Todas as Compras-------------");

		Iterable<LoteCompra> lista = controle.listarLotes();
		for(LoteCompra lote: lista) {
				System.out.println(lote);
				System.out.println("\n");
		}
		System.out.println("-----------------------Fim------------------------");
		System.out.println();
	}
	private static void visualizarTransito() {
		System.out.println("--------Listando Compras Em Transito--------");

		Iterable<LoteCompra> lista = controle.listarLotes();
		for(LoteCompra lote: lista) {
			if(lote.getStatus().equals("transito")){
				System.out.println(lote);
				System.out.println("\n");
			}
		}
		System.out.println("--------------------Fim---------------------");
		System.out.println();
	}
	
	private static void recebimentoLote(Scanner scanner) {
		System.out.println("-------------------Recebimento--------------------");
		visualizarTransito();
		

		System.out.print("Digite o id do pedido de compra: ");
		int id=scanner.nextInt();
		LoteCompra lote = controle.buscaLotePeloId(id);

		lote=controle.recebeLote(lote);
		
		System.out.print("Recebimento Concluido!\n");
		System.out.println(lote);
		System.out.println("--------------------------------------------------");
	}
	
	public static void menuVendas(Scanner scanner) {
		boolean gatilho=true;
		while(gatilho) {
			System.out.println("\nEscolha acao");
			System.out.println("0-voltar");
			System.out.println("1-Nova Venda");
			System.out.println("2-Listar Vendas");
			System.out.println("3-Listar Produtos Em Maos");
			System.out.println("4-Devolucao");

			
			int opcao=scanner.nextInt();
			
			switch(opcao) {
			case 1:
				cadastrarVenda(scanner);
				break;
			case 2:
				visualizarVendas();
				break;
			case 3:
				visualizarLotesEmMaos();
				break;
			case 4:
				devolucaoVenda(scanner);
				break;
			default:
				gatilho=false;
			}
			
		}
		System.out.println();
	}
	private static void visualizarLotesEmMaos() {
		System.out.println("-----------Listando Lotes Em Maos-----------");
		Iterable<LoteCompra> lista = controle.listarLotes();
		for(LoteCompra lote: lista) {
			if(lote.getStatus().equals("em maos")){
				System.out.println(lote.listagemParaVenda());
			}
		}
		System.out.println("--------------------Fim---------------------");
	}
	private static void cadastrarVenda(Scanner scanner) {
		System.out.println("--------------Cadastrando nova venda--------------");
		visualizarLotesEmMaos();
		System.out.print("Digite o Id do lote: ");
		int idlote=scanner.nextInt();
		
		LoteCompra lote = controle.buscaLotePeloId(idlote);

		int qtdDisponivel=lote.getQtd()-lote.getQtdVendida();
		if(lote.getStatus().equals("em maos")) {//tem quantidade disponivel>0
			
			System.out.println("Produto:["+lote.getNomeProduto()+"]");
			System.out.println("Quantidade Disponivel:["+qtdDisponivel+"]");

			System.out.print("Qtd vendida:");
			int qtd=scanner.nextInt();
			
			
			boolean filtro=true;
			while(filtro) {
				if(qtd>qtdDisponivel) {
					System.out.print("Quantidade inserida ["+qtd+"] maior do que quantidade disponivel ["+qtdDisponivel+"]" );
					System.out.print( "\nDigite um valor valido: ");
					qtd=scanner.nextInt();
				}else {//digitou quantidade valida

					filtro=false;
				}
			}

			
			System.out.print("Preco unitario:");
			float precoVenda=scanner.nextFloat();
			float valorTotal=qtd*precoVenda;
			
			System.out.print("nome cliente: ");
			String nome=scanner.next();
			
			float lucro=(precoVenda-lote.getCusto())*qtd;
			
			Venda novaVenda=controle.adicionarNovaVenda(nome,precoVenda,lote,qtd,lucro);
			
						

			System.out.println("Salvo\n");
			System.out.println(novaVenda);
			
			
			
		}else {
			System.out.print("Pedido de compra nao disponivel [status="+lote.getStatus()+"]");
		}
		System.out.println("--------------------------------------------------");

	}
	private static void visualizarVendas() {
		System.out.println("-----------------Listando Vendas------------------");


		Iterable<Venda> lista = controle.listarVendas();
		for(Venda venda: lista) {
				System.out.println(venda);
				System.out.println("\n");
		}
		System.out.println("-----------------------Fim------------------------");
		System.out.println();
	}

	private static void devolucaoVenda(Scanner scanner) {
		System.out.print("-------------Nova devolucao de venda--------------");
		visualizarVendas();
		
		System.out.print("Digite o id da venda: ");
		int id=scanner.nextInt();
		Venda venda = controle.buscaVendaPeloId(id);

			
		venda=controle.entradaDevolucao(venda);
		

		System.out.println("Concluido!\n");
		System.out.println("Venda devolvida: ");
		System.out.println(venda);
		System.out.println("------------------------------");
		System.out.println("Item retornado ao estoque: ");
		System.out.println(venda.getLote());
		System.out.println("--------------------------------------------------");


	}
	
	public static void menuRelatorios() {
		System.out.println("-----------------Situacao Atual:------------------");
		System.out.println(controle.resumoLote("em transito"));
		System.out.println(controle.resumoLote("em maos"));
		System.out.println(controle.resumoVendas());
		System.out.println(controle.resumoCaixa());
		System.out.println("--------------------------------------------------\n");
		
	}

}
