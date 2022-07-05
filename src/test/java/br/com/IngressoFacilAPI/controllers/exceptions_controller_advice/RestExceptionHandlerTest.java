package br.com.IngressoFacilAPI.controllers.exceptions_controller_advice;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.IngressoFacilAPI.entities.Local.form.LocalForm;
import br.com.IngressoFacilAPI.entities.carrinho.form.CarrinhoForm;
import br.com.IngressoFacilAPI.entities.cliente.form.ClienteCadastroForm;
import br.com.IngressoFacilAPI.entities.evento.form.EventoForm;
import br.com.IngressoFacilAPI.entities.login.form.LoginForm;
import br.com.IngressoFacilAPI.util.Util;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("testes")
class RestExceptionHandlerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	
	private final String eventoUri="/v1/admin/evento";
	private final String id="/{id}";
	private final String eventoUriId= eventoUri+id;
	
	private final String localUri="/v1/admin/local";
	private final String localUriId= localUri+id;
	
	private final String authUri="/v1/auth";
	
	private final String cadastroClienteUri="/v1/cadastrarCliente";
	private final String carrinhoUri= "/v1/carrinho";
	
			
	@Test
	public void eventoController_cadastrar_deveRetornarBadRequestEInformarMensagensDeErro_QuandoReceberUmEventoFormComQualquerAtributoNuloOuEmBranco() throws Exception {
		ResultActions resultActions = mockMvc
		.perform(MockMvcRequestBuilders
				.post(eventoUri)
				.content(objectMapper.writeValueAsString(new EventoForm()))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isBadRequest());
		
		String errosNoFormulario = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		assertThat(errosNoFormulario.contains("\"É obrigatório informar um nome!\"")).isEqualTo(true);
		assertThat(errosNoFormulario.contains("\"É obrigatório informar o valor do ingresso!\"")).isEqualTo(true);
		assertThat(errosNoFormulario.contains("\"É obrigatório informar a quantidade de ingressos disponível!\"")).isEqualTo(true);
		assertThat(errosNoFormulario.contains("\"É obrigatório informar a data do evento!\"")).isEqualTo(true);
		assertThat(errosNoFormulario.contains("\"É obrigatório informar a hora do evento!\"")).isEqualTo(true);
		assertThat(errosNoFormulario.contains("\"É obrigatório informar a o tipo de evento! (Ex: MUSICAL, TREINAMENTO, TEATRO...)\"")).isEqualTo(true);
		assertThat(errosNoFormulario.contains("\"É obrigatório informar o Id!\"")).isEqualTo(true);			
	}
	
	@Test
	public void eventoController_procurar_deveRetornarNotFoundEMensagemDeErro_QuandoReceberUmIdDeEventoQueNaoExiste() throws Exception {
		ResultActions resultActions = mockMvc
		.perform(MockMvcRequestBuilders
				.get(eventoUriId, -1L))
		.andExpect(MockMvcResultMatchers
				.status()
				.isNotFound());
		
		String mensagemDaException = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		assertThat(mensagemDaException.equalsIgnoreCase("O id do evento informado não existe!")).isEqualTo(true);
	}
	
	@Test
	public void eventoController_atualizar_deveRetornarBadRequestEInformarMensagensDeErro_QuandoReceberUmEventoFormComQualquerAtributoNuloOuEmBranco() throws Exception {
		ResultActions resultActions = mockMvc
		.perform(MockMvcRequestBuilders
				.put(eventoUriId,1L)
				.content(objectMapper.writeValueAsString(new EventoForm()))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isBadRequest());
		
		String errosNoFormulario = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		assertThat(errosNoFormulario.contains("\"É obrigatório informar um nome!\"")).isEqualTo(true);
		assertThat(errosNoFormulario.contains("\"É obrigatório informar o valor do ingresso!\"")).isEqualTo(true);
		assertThat(errosNoFormulario.contains("\"É obrigatório informar a quantidade de ingressos disponível!\"")).isEqualTo(true);
		assertThat(errosNoFormulario.contains("\"É obrigatório informar a data do evento!\"")).isEqualTo(true);
		assertThat(errosNoFormulario.contains("\"É obrigatório informar a hora do evento!\"")).isEqualTo(true);
		assertThat(errosNoFormulario.contains("\"É obrigatório informar a o tipo de evento! (Ex: MUSICAL, TREINAMENTO, TEATRO...)\"")).isEqualTo(true);
		assertThat(errosNoFormulario.contains("\"É obrigatório informar o Id!\"")).isEqualTo(true);			
	}
	
	@Test
	public void eventoController_atualizar_deveRetornarNotFoundEInformarMensagemDeErro_QuandoReceberUmIdDeEventoQueNaoExiste() throws Exception {
		ResultActions resultActions = mockMvc
		.perform(MockMvcRequestBuilders
				.put(eventoUriId,-1L)
				.content(objectMapper.writeValueAsString(Util.criarEventoForm()))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isNotFound());
		
		String mensagemDaException = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		assertThat(mensagemDaException.equalsIgnoreCase("O id do evento informado não existe!")).isEqualTo(true);
	}
	
	@Test
	public void eventoController_deletar_deveRetornarNotFoundEInformarMensagemDeErro_QuandoReceberUmIdDeEventoQueNaoExiste() throws Exception {
		ResultActions resultActions = mockMvc
		.perform(MockMvcRequestBuilders
				.delete(eventoUriId,-1L)
				.content(objectMapper.writeValueAsString(Util.criarEventoForm()))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isNotFound());
		
		String mensagemDaException = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		assertThat(mensagemDaException.equalsIgnoreCase("O id do evento informado não existe!")).isEqualTo(true);
	}
		
	@Test
	public void localController_cadastrar_deveRetornarBadRequestEInformarMensagensDeErro_QuandoReceberUmLocalFormComQualquerAtributoNuloOuEmBranco() throws Exception {
		ResultActions resultActions = mockMvc
		.perform(MockMvcRequestBuilders
				.post(localUri)
				.content(objectMapper.writeValueAsString(new LocalForm()))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isBadRequest());
		
		String errosNoFormulario = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		assertThat(errosNoFormulario.contains("\"É obrigatório informar um nome!\"")).isEqualTo(true);
		assertThat(errosNoFormulario.contains("\"É obrigatório informar um endereço!\"")).isEqualTo(true);		
	}
	
	@Test
	public void localController_procurar_deveRetornarNotFoundEMensagemDeErro_QuandoReceberUmIdDeLocalQueNaoExiste() throws Exception {
		ResultActions resultActions = mockMvc
		.perform(MockMvcRequestBuilders
				.get(localUriId, -1L))
		.andExpect(MockMvcResultMatchers
				.status()
				.isNotFound());
		
		String mensagemDaException = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		assertThat(mensagemDaException.equalsIgnoreCase("O id do local informado não existe!")).isEqualTo(true);
	}
	
	@Test
	public void localController_atualizar_deveRetornarBadRequestEInformarMensagensDeErro_QuandoReceberUmLocalFormComQualquerAtributoNuloOuEmBranco() throws Exception {
		ResultActions resultActions = mockMvc
		.perform(MockMvcRequestBuilders
				.put(localUriId,1L)
				.content(objectMapper.writeValueAsString(new LocalForm()))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isBadRequest());
		
		String errosNoFormulario = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		assertThat(errosNoFormulario.contains("\"É obrigatório informar um nome!\"")).isEqualTo(true);
		assertThat(errosNoFormulario.contains("\"É obrigatório informar um endereço!\"")).isEqualTo(true);
	}
	
	@Test
	public void localController_atualizar_deveRetornarNotFoundEInformarMensagemDeErro_QuandoReceberUmIdDeLocalQueNaoExiste() throws Exception {
		ResultActions resultActions = mockMvc
		.perform(MockMvcRequestBuilders
				.put(localUriId,-1L)
				.content(objectMapper.writeValueAsString(Util.criarLocalForm()))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isNotFound());
		
		String mensagemDaException = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		assertThat(mensagemDaException.equalsIgnoreCase("O id do local informado não existe!")).isEqualTo(true);
	}
	
	@Test
	public void localController_deletar_deveRetornarNotFoundEInformarMensagemDeErro_QuandoReceberUmIdDeLocalQueNaoExiste() throws Exception {
		ResultActions resultActions = mockMvc
		.perform(MockMvcRequestBuilders
				.delete(localUriId,-1L)
				.content(objectMapper.writeValueAsString(Util.criarLocalForm()))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isNotFound());
		
		String mensagemDaException = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		assertThat(mensagemDaException.equalsIgnoreCase("O id do local informado não existe!")).isEqualTo(true);
	}
	
	@Test
	public void localController_deletar_deveRetornarConflictEInformarMensagemDeErro_QuandoReceberUmIdDeLocalQueEstaAtreladoAUmEvento() throws Exception {
		ResultActions resultActions = mockMvc
		.perform(MockMvcRequestBuilders
				.delete(localUriId,3L)
				.content(objectMapper.writeValueAsString(Util.criarLocalForm()))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isConflict());
		
		String mensagemDaException = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		assertThat(mensagemDaException.equalsIgnoreCase("Não é possivel deletar este recurso, pois outros dependem dele")).isEqualTo(true);
	}
	
	@Test
	public void autenticacaoController_autenticar_deveRetornarBadRequestEInformarMensagensDeErro_QuandoReceberUmLoginFormDadosIncorretos() throws Exception {
		ResultActions resultActions = mockMvc
		.perform(MockMvcRequestBuilders
				.post(authUri)
				.content(objectMapper.writeValueAsString(Util.criarLoginFormInvalido()))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isBadRequest());
		
		String errosNoFormulario = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		assertThat(errosNoFormulario).isEqualTo("Bad credentials");	
	}
	
	@Test
	public void autenticacaoController_autenticar_deveRetornarBadRequestEInformarMensagensDeErro_QuandoReceberUmLoginFormComQualquerAtributoNuloOuEmBranco() throws Exception {
		ResultActions resultActions = mockMvc
		.perform(MockMvcRequestBuilders
				.post(authUri)
				.content(objectMapper.writeValueAsString(new LoginForm()))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isBadRequest());
		
		String errosNoFormulario = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		assertThat(errosNoFormulario.contains("\"É obrigatório informar um email!\"")).isEqualTo(true);
		assertThat(errosNoFormulario.contains("\"É obrigatório informar uma senha com no mínimo 8 caracteres!\"")).isEqualTo(true);	
	}
	
	@Test
	public void cadastroClientesController_cadastrarCliente_deveRetornarBadRequestEInformarMensagensDeErro_QuandoReceberUmClienteCadastroFormComQualquerAtributoNuloOuEmBranco() throws Exception {
		ResultActions resultActions = mockMvc
		.perform(MockMvcRequestBuilders
				.post(cadastroClienteUri)
				.content(objectMapper.writeValueAsString(new ClienteCadastroForm()))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isBadRequest());
		
		String errosNoFormulario = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

		assertThat(errosNoFormulario.contains("\"É obrigatório informar um nome!\"")).isEqualTo(true);
		assertThat(errosNoFormulario.contains("\"É obrigatório informar um email!\"")).isEqualTo(true);
		assertThat(errosNoFormulario.contains("\"É obrigatório informar uma senha com no mínimo 8 caracteres!\"")).isEqualTo(true);	
	}
	
	@Test
	public void cadastroClientesController_cadastrarCliente_deveRetornarBadRequestEInformarMensagensDeErro_QuandoReceberUmClienteCadastroFormComEmailQueJaExisteNoBancoDeDados() throws Exception {
		ResultActions resultActions = mockMvc
		.perform(MockMvcRequestBuilders
				.post(cadastroClienteUri)
				.content(objectMapper.writeValueAsString(Util.criarClienteForm()))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isBadRequest());
		
		String errosNoFormulario = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

		assertThat(errosNoFormulario.contains("\"Este email já está cadastrado!\"")).isEqualTo(true);
	}
	
	@Test
	public void carrinhoController_atualizarCarrinho_deveRetornarBadRequestEInformarMensagensDeErro_QuandoReceberUmCarrinhoFormComQualquerAtributoNuloOuEmBranco() throws Exception {
		ResultActions resultActions = mockMvc
		.perform(MockMvcRequestBuilders
				.put(carrinhoUri)
				.content(objectMapper.writeValueAsString(new CarrinhoForm()))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isBadRequest());
		
		String errosNoFormulario = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

		assertThat(errosNoFormulario.contains("\"É obrigatório informar o Id!\"")).isEqualTo(true);
		assertThat(errosNoFormulario.contains("\"É obrigatório informar a quantidade de ingressos que deseja!\"")).isEqualTo(true);	
	}

	
}
