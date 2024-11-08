package io.github.evaldo.ibm.service;

import io.github.evaldo.ibm.dto.transacao.TransacaoRequest;
import io.github.evaldo.ibm.dto.transacao.TransacaoResponse;
import io.github.evaldo.ibm.model.Transacao;

import java.util.List;

public interface TransacaoService {

    List<TransacaoResponse> findAllTransacoesByContaId(Long contaId);

    Long createTransacao(TransacaoRequest transacaoRequest);

    Transacao findTransacaoById(Long id);


}
