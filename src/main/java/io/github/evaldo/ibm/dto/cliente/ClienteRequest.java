package io.github.evaldo.ibm.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClienteRequest {

    private String nome;

    private Integer idade;

    private String email;

    private String numeroConta;
}
