package br.com.IngressoFacilAPI.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.IngressoFacilAPI.entities.Local.Local;
import br.com.IngressoFacilAPI.entities.Local.form.LocalForm;
import br.com.IngressoFacilAPI.entities.carrinho.Carrinho;
import br.com.IngressoFacilAPI.entities.cliente.Cliente;
import br.com.IngressoFacilAPI.entities.evento.Evento;
import br.com.IngressoFacilAPI.entities.evento.TipoDeEvento;
import br.com.IngressoFacilAPI.entities.usuario.Usuario;

public class Util {
		
	public static Pageable criarPageable() {
		return new PageImpl<>(List.of("")).getPageable();
	}
	
	public static Local criarLocal() {
		return Local.builder()
		.nome("TESTE")
		.endereco("RUA TESTE")
		.build();
	}
	
	public static Evento criarEvento() {
		Local local = criarLocal();
		local.setId(1L);
		return Evento.builder()
				.nome("NOME TESTE")
				.valor(new BigDecimal(150))
				.tipo(TipoDeEvento.MUSICAL)
				.local(local)
				.dataEvento(LocalDate.now())
				.horaEvento(LocalTime.now())
				.quantidadeIngressos(100)
				.quantidadeIngressosDisponiveis(100)
				.quantidadeIngressosVendidos(0)
				.caminhoImagemDoEvento("/imagensDosEventos/imagemPadrao.jpg")
				.build();
	}
	
	public static Usuario criarUsuario() {
		return Usuario.builder()
				.email("teste@teste.com")
				.senha("TESTE")
				.build();
	}
	
	public static Cliente criarCliente() {
		return Cliente.builder()
				.email("teste@teste.com")
				.nome("TESTE")
				.build();
	}

	public static Carrinho criarCarrinho() {
		return Carrinho.builder()
				.clienteEmail("teste@teste.com")
				.eventoId(1L)
				.quantidadeIngressos(10)
				.build();
	}
	
	public static LocalForm criarLocalForm() {
		LocalForm form = new LocalForm();
		form.setEndereco("TESTE");
		form.setNome("TESTE");
		return form;
	}

	
}
