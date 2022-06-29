package br.com.IngressoFacilAPI.services.envio_de_emails;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailSenderService {
	
	private final JavaMailSender enviaEmail;
	private final String remetente="testesdeapp.naoresponda@gmail.com";
	
	@Async
	public void enviarEmail(String destinatario, EmailsTemplates emailTemplate) {
		SimpleMailMessage mensagem= new SimpleMailMessage();
		mensagem.setFrom(remetente);
		mensagem.setTo(destinatario);
		mensagem.setText(emailTemplate.getText());
		mensagem.setSubject(emailTemplate.getSubject());
		
		enviaEmail.send(mensagem);
	}
}
