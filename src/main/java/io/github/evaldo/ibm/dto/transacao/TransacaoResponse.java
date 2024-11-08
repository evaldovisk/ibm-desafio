package io.github.evaldo.ibm.dto.transacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TransacaoResponse {

    private Long idTransacao;
    private String transacao;
    private String tipo;
    private String nomeDestino;
    private String conta;
    private Double valor;
    private String dataTransacao;


}
