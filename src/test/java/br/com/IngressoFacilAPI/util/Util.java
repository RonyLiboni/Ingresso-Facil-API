package br.com.IngressoFacilAPI.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;

import br.com.IngressoFacilAPI.entities.Local.Local;
import br.com.IngressoFacilAPI.entities.Local.form.LocalForm;
import br.com.IngressoFacilAPI.entities.carrinho.Carrinho;
import br.com.IngressoFacilAPI.entities.cliente.Cliente;
import br.com.IngressoFacilAPI.entities.cliente.form.ClienteCadastroForm;
import br.com.IngressoFacilAPI.entities.evento.Evento;
import br.com.IngressoFacilAPI.entities.evento.TipoDeEvento;
import br.com.IngressoFacilAPI.entities.evento.form.EventoForm;
import br.com.IngressoFacilAPI.entities.ingresso.Ingresso;
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
	
	public static LocalForm criarLocalForm() {
		LocalForm form = new LocalForm();
		form.setEndereco("TESTE FORM");
		form.setNome("TESTE FORM");
		return form;
	}
	
	public static Evento criarEvento() {
		Local local = criarLocal();
		local.setId(1L);
		return Evento.builder()
				.nome("TESTE")
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
	
	public static EventoForm criarEventoForm() {
		return EventoForm.builder()
				.nome("TESTE FORM")
				.valor(new BigDecimal(150))
				.tipo(TipoDeEvento.MUSICAL)
				.localId(1L)
				.dataEvento(LocalDate.now())
				.horaEvento(LocalTime.now())
				.quantidadeIngressos(100)
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
	
	public static ClienteCadastroForm criarClienteForm() {
		ClienteCadastroForm clienteCadastroForm = new ClienteCadastroForm();
		clienteCadastroForm.setEmail("teste@teste.com");
		clienteCadastroForm.setNome("TESTE FORM");
		return clienteCadastroForm;
	}

	public static Carrinho criarCarrinho() {
		return Carrinho.builder()
				.clienteEmail("teste@teste.com")
				.eventoId(1L)
				.quantidadeIngressos(10)
				.build();
	}

	public static Ingresso criarIngresso() {
		return Ingresso.builder()
				.evento(Util.criarEvento())
				.build();
	}

	public static MockMultipartFile criarUmMultiPart() {
		return new MockMultipartFile("TESTE", "OriginalFileName","PICTURE/PNG",Util.criarEvento().getNome().getBytes());
	}
	
	public static MockMultipartFile criarUmMultiPartVazio() throws FileNotFoundException, IOException {
		return new MockMultipartFile(null, new FileInputStream(""));
	}

	public static boolean apagarArquivoTeste(String caminhoDoArquivo) throws IOException {
		return Files.deleteIfExists(new File(caminhoDoArquivo).toPath());
	}



	
}
