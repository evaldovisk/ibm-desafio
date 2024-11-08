package io.github.evaldo.ibm.service.implementation;

import io.github.evaldo.ibm.dto.transacao.TransacaoRequest;
import io.github.evaldo.ibm.dto.transacao.TransacaoResponse;
import io.github.evaldo.ibm.exceptions.BadRequestException;
import io.github.evaldo.ibm.exceptions.ObjectNotFoundException;
import io.github.evaldo.ibm.model.Transacao;
import io.github.evaldo.ibm.repository.TransacaoRepository;
import io.github.evaldo.ibm.service.TransacaoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static io.github.evaldo.ibm.Utils.ConversorData.formatarDataDdMmYyyy;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    private final TransacaoRepository transacaoRepository;

    public TransacaoServiceImpl(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @Override
    @Transactional
    public List<TransacaoResponse> findAllTransacoesByContaId(Long id) {
        try {
            return transacaoRepository.findAllByIdOrigemOrIdDestino(id).stream().map(
                    transacao -> {
                        String typeTransacao = (Objects.equals(id, transacao.getOrigem().getId()) ? "Saida" : "Entrada");

                        return TransacaoResponse.builder()
                                .idTransacao(transacao.getId())
                                .transacao(typeTransacao)
                                .tipo("Transferência")
                                .nomeDestino(getNomeDestino(transacao, typeTransacao))
                                .valor(transacao.getValor())
                                .conta(getContaDestino(transacao, typeTransacao))
                                .dataTransacao(formatarDataDdMmYyyy(transacao.getDataTransacao()))
                                .build();
                    }
            ).toList();
        } catch (Exception e) {
            throw new BadRequestException("Erro inesperado ao buscar transações:", e);
        }
    }

    @Override
    @Transactional
    public Long createTransacao(TransacaoRequest transacaoRequest) {
        try {
            return transacaoRepository.realizarTransferencia(
                    transacaoRequest.getOrigem(),
                    transacaoRequest.getDestino(),
                    transacaoRequest.getValor());
        } catch (Exception e) {
            throw new BadRequestException("Erro inesperado ao realizar a transferência:", e);
        }
    }

    @Override
    @Transactional
    public Transacao findTransacaoById(Long id) {
        try {
            return transacaoRepository.findById(id).orElseThrow(
                    () -> new ObjectNotFoundException("Transação não encontrada")
            );
        } catch (ObjectNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new BadRequestException("Erro inesperado ao buscar transação:", e);
        }
    }

    private String getNomeDestino(Transacao transacao, String Tipo) {
        if (Tipo.equals("Entrada")) {
            return transacao.getOrigem().getCliente().getNome();
        }
        return transacao.getDestino().getCliente().getNome();
    }

    private String getContaDestino(Transacao transacao, String Tipo) {
        if (Tipo.equals("Entrada")) {
            return transacao.getOrigem().getNumeroConta();
        }
        return transacao.getDestino().getNumeroConta();
    }

}
