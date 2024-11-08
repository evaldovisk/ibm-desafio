package io.github.evaldo.ibm.dto.transacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TransacaoRequest {

    private Integer origem;
    private Integer destino;
    private BigDecimal valor;

}
