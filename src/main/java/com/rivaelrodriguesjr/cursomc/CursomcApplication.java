package com.rivaelrodriguesjr.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rivaelrodriguesjr.cursomc.domains.Categoria;
import com.rivaelrodriguesjr.cursomc.domains.Cidade;
import com.rivaelrodriguesjr.cursomc.domains.Cliente;
import com.rivaelrodriguesjr.cursomc.domains.Endereco;
import com.rivaelrodriguesjr.cursomc.domains.Estado;
import com.rivaelrodriguesjr.cursomc.domains.Pagamento;
import com.rivaelrodriguesjr.cursomc.domains.PagamentoComBoleto;
import com.rivaelrodriguesjr.cursomc.domains.PagamentoComCartao;
import com.rivaelrodriguesjr.cursomc.domains.Pedido;
import com.rivaelrodriguesjr.cursomc.domains.Produto;
import com.rivaelrodriguesjr.cursomc.domains.enums.EstadoPagamento;
import com.rivaelrodriguesjr.cursomc.domains.enums.TipoCliente;
import com.rivaelrodriguesjr.cursomc.repositories.CategoriaRepository;
import com.rivaelrodriguesjr.cursomc.repositories.CidadeRepository;
import com.rivaelrodriguesjr.cursomc.repositories.ClienteRepository;
import com.rivaelrodriguesjr.cursomc.repositories.EnderecoRepository;
import com.rivaelrodriguesjr.cursomc.repositories.EstadoRepository;
import com.rivaelrodriguesjr.cursomc.repositories.PagamentoRepository;
import com.rivaelrodriguesjr.cursomc.repositories.PedidoRepository;
import com.rivaelrodriguesjr.cursomc.repositories.ProdutoRepository;

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
	PagamentoRepository pagamentoRepository;
	@Autowired 
	PedidoRepository pedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));	
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com","36378912377", TipoCliente.PESSOAFISICA);
		
		String t1 = "27363323";
		String t2 = "93838393";
		cli1.getTelefones().addAll(Arrays.asList(t1, t2));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf1.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf1.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf1.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
	}

}
