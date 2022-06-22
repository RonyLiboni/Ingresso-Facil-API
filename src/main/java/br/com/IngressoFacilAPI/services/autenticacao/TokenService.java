package br.com.IngressoFacilAPI.services.autenticacao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.IngressoFacilAPI.entities.login.form.LoginForm;
import br.com.IngressoFacilAPI.entities.usuario.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {

	@Value("${ingressofacilapi.jwt.expiration}")
	private String expiration;
	
	@Value("${ingressofacilapi.jwt.secret}")
	private String secret;
		
	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
	
	public String criarToken(LoginForm form, AuthenticationManager authManager) {
		return gerarToken(criarAuthentication(form, authManager));
	}

	private Authentication criarAuthentication(LoginForm form, AuthenticationManager authManager) {
		return authManager.authenticate(new UsernamePasswordAuthenticationToken(form.getEmail(), form.getSenha()));
	}
	
	private String gerarToken(Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
	
		return Jwts.builder()
				.setIssuer("Ingresso Facil API")
				.setSubject(usuario.getId().toString())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + Long.parseLong(expiration)))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
}
