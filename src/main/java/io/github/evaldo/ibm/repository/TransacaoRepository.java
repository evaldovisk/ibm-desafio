package io.github.evaldo.ibm.repository;

import io.github.evaldo.ibm.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    @Procedure(name = "Transacao.realizarTransferencia")
    Long realizarTransferencia(
            @Param("proc_id_origem") Integer idOrigem,
            @Param("proc_id_destino") Integer idDestino,
            @Param("proc_valor") BigDecimal valor
    );
    @Query("SELECT t FROM Transacao t WHERE t.origem.id = :id OR t.destino.id = :id")
    List<Transacao> findAllByIdOrigemOrIdDestino(@Param("id") Long id);


}
