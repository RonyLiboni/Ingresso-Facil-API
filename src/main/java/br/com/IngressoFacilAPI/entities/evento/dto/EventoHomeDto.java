package br.com.IngressoFacilAPI.entities.evento.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import br.com.IngressoFacilAPI.entities.Local.Local;
import br.com.IngressoFacilAPI.entities.evento.Evento;
import br.com.IngressoFacilAPI.entities.evento.TipoDeEvento;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventoHomeDto {
	private Long id;
	private String nome;
	private BigDecimal valor;
	private TipoDeEvento tipo;
	private Local local;
	private LocalDate dataEvento;
	private LocalTime horaEvento;
	private String caminhoImagemDoEvento;
	private Integer quantidadeIngressosDisponiveis;

	public EventoHomeDto(Evento evento) {
		this.id = evento.getId();
		this.nome = evento.getNome();
		this.valor = evento.getValor();
		this.tipo = evento.getTipo();
		this.local = evento.getLocal();
		this.dataEvento = evento.getDataEvento();
		this.horaEvento = evento.getHoraEvento();
		this.caminhoImagemDoEvento = evento.getCaminhoImagemDoEvento();
		this.quantidadeIngressosDisponiveis =  evento.getQuantidadeIngressosDisponiveis();

	}
}
