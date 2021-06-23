package com.telmoricardo.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.telmoricardo.cursomc.domain.Categoria;
import com.telmoricardo.cursomc.domain.Cidade;
import com.telmoricardo.cursomc.domain.Cliente;
import com.telmoricardo.cursomc.domain.Endereco;
import com.telmoricardo.cursomc.domain.Estado;
import com.telmoricardo.cursomc.domain.ItemPedido;
import com.telmoricardo.cursomc.domain.Pagamento;
import com.telmoricardo.cursomc.domain.PagamentoComBoleto;
import com.telmoricardo.cursomc.domain.PagamentoComCartao;
import com.telmoricardo.cursomc.domain.Pedido;
import com.telmoricardo.cursomc.domain.Produto;
import com.telmoricardo.cursomc.domain.enums.EstadoPagamento;
import com.telmoricardo.cursomc.domain.enums.TipoCliente;
import com.telmoricardo.cursomc.repositories.CategoriaRepository;
import com.telmoricardo.cursomc.repositories.CidadeRepository;
import com.telmoricardo.cursomc.repositories.ClienteRepository;
import com.telmoricardo.cursomc.repositories.EnderecoRepository;
import com.telmoricardo.cursomc.repositories.EstadoRepository;
import com.telmoricardo.cursomc.repositories.ItemPedidoRepository;
import com.telmoricardo.cursomc.repositories.PagamentoRepository;
import com.telmoricardo.cursomc.repositories.PedidoRepository;
import com.telmoricardo.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama");
		Categoria cat4 = new Categoria(null, "Mesa");
		Categoria cat5 = new Categoria(null, "Banho");
		Categoria cat6 = new Categoria(null, "Jardinagem");
		Categoria cat7 = new Categoria(null, "Totem");
		Categoria cat8 = new Categoria(null, "Camisetas");
		Categoria cat9 = new Categoria(null, "Roupas");
		Categoria cat10 = new Categoria(null, "Acessórios");
		Categoria cat11 = new Categoria(null, "Eletronicos");
		
		Produto p1 = new Produto(null, "Computado", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));	
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2,cat3, cat4, cat5, cat6,cat7,cat8,cat9,cat10,cat11));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);		
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISISCA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("03/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip3));
		ped2.getItens().addAll(Arrays.asList(ip2));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}
