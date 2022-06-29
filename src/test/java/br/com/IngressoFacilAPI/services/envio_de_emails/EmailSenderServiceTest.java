package br.com.IngressoFacilAPI.services.envio_de_emails;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import br.com.IngressoFacilAPI.services.administracao_plataforma.ServiceTestConfig;

class EmailSenderServiceTest extends ServiceTestConfig{

	@InjectMocks
	private EmailSenderService emailSenderService;
	@Mock
	private JavaMailSender javaMailSenderMock;
	@Captor
	private ArgumentCaptor<SimpleMailMessage> captor;
	private final String destinatario = "teste@teste.com";
	
	@Test
	void enviarEmail_DeveRetornarUmEmailNaoNulo() {
		emailSenderService.enviarEmail(destinatario, EmailsTemplates.COMPRA_REALIZADA_COM_SUCESSO);
		BDDMockito.verify(javaMailSenderMock).send(captor.capture());
		SimpleMailMessage mensagem = captor.getValue();
		assertThat(mensagem).isNotNull();
		String from = mensagem.getFrom();
		assertThat(from).isEqualTo("testesdeapp.naoresponda@gmail.com");
		assertThat(mensagem.getTo()[0]).isEqualTo(destinatario);
	}

}
