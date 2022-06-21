package br.com.IngressoFacilAPI.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailSenderService {
	
	private final JavaMailSender enviaEmail;
	
	@Async
	public void enviarEmail(String destinatario, String corpo, String assunto) {
		SimpleMailMessage mensagem= new SimpleMailMessage();
		mensagem.setFrom("ronaldliboni@gmail.com");
		mensagem.setTo(destinatario);
		mensagem.setText(assunto);
		mensagem.setSubject(corpo);
		
		enviaEmail.send(mensagem);
	}
}
