package io.github.evaldo.ibm.dto.cliente;

import io.github.evaldo.ibm.model.Conta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClienteResponse {

    private Long id;

    private String nome;

    private Integer idade;

    private String email;

    private Conta conta;

    private String numeroConta;

}
