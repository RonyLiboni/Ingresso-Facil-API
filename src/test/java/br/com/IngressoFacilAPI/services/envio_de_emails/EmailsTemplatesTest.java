package br.com.IngressoFacilAPI.services.envio_de_emails;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class EmailsTemplatesTest {
	
	private String mensagemConta= "Olá, email para sinalizar que sua conta foi criada com sucesso!"
			+ "\n https://github.com/RonyLiboni/Ingresso-Facil-API.git";
	private String assuntoConta= "Cadastro no site da Ingresso Facil foi um sucesso!";
	private String assuntoCompra= "A compra dos seus ingressos foi realizada com sucesso!";
	private String mensagemCompra= "Olá, email para sinalizar que sua compra dos seus ingressos foi realizada com sucesso!" +
			"\n https://github.com/RonyLiboni/Ingresso-Facil-API.git";
	
	@Test
	void deveRetornarCorpoEAssuntoDoEmail_QuandoCONTA_CRIADA_COM_SUCESSO() {
		assertThat(EmailsTemplates.CONTA_CRIADA_COM_SUCESSO.getText()).isEqualTo(mensagemConta);
		assertThat(EmailsTemplates.CONTA_CRIADA_COM_SUCESSO.getSubject()).isEqualTo(assuntoConta);	
	}
	
	@Test
	void deveRetornarCorpoEAssuntoDoEmail_QuandoCOMPRA_REALIZADA_COM_SUCESSO() {
		assertThat(EmailsTemplates.COMPRA_REALIZADA_COM_SUCESSO.getText()).isEqualTo(mensagemCompra);
		assertThat(EmailsTemplates.COMPRA_REALIZADA_COM_SUCESSO.getSubject()).isEqualTo(assuntoCompra);	
	}

}
