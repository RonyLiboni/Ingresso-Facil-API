package br.com.IngressoFacilAPI.entities.evento.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import br.com.IngressoFacilAPI.entities.Local.dto.LocalDto;
import br.com.IngressoFacilAPI.entities.evento.Evento;
import br.com.IngressoFacilAPI.entities.evento.TipoDeEvento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventoIngressoDto {
	private Long id;
	private String nome;
	private BigDecimal valor;
	private TipoDeEvento tipo;
	private LocalDto local;
	private LocalDate dataEvento;
	private LocalTime horaEvento;
	private String caminhoImagemDoEvento;

	public EventoIngressoDto(Evento evento) {
		this.id = evento.getId();
		this.nome = evento.getNome();
		this.valor = evento.getValor();
		this.tipo = evento.getTipo();
		this.local = new LocalDto(evento.getLocal());
		this.dataEvento = evento.getDataEvento();
		this.horaEvento = evento.getHoraEvento();
		this.caminhoImagemDoEvento = evento.getCaminhoImagemDoEvento();
	}
}
