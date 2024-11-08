package io.github.evaldo.ibm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedStoredProcedureQuery(
        name = "Transacao.realizarTransferencia",
        procedureName = "realizar_transferencia",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "proc_id_origem", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "proc_id_destino", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "proc_valor", type = BigDecimal.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "transacao_id", type = Integer.class),

        }
)
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_origem", referencedColumnName = "id", nullable = false)
    private Conta origem;

    @OneToOne
    @JoinColumn(name = "id_destino", referencedColumnName = "id", nullable = false)
    private Conta destino;

    private String tipo = "transferencia";

    @Column(nullable = false)
    private Double valor;

    private Date dataTransacao;
}
