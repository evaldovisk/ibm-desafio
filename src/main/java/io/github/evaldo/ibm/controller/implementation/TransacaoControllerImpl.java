package io.github.evaldo.ibm.controller.implementation;

import io.github.evaldo.ibm.controller.TransacaoController;
import io.github.evaldo.ibm.dto.DefaultHttpMessageResponse;
import io.github.evaldo.ibm.dto.cliente.ClienteResponse;
import io.github.evaldo.ibm.dto.transacao.TransacaoRequest;
import io.github.evaldo.ibm.dto.transacao.TransacaoResponse;
import io.github.evaldo.ibm.model.Transacao;
import io.github.evaldo.ibm.service.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class TransacaoControllerImpl implements TransacaoController {

    private final TransacaoService service;

    public TransacaoControllerImpl(TransacaoService service){
        this.service = service;
    }

    @Override
    public ResponseEntity<DefaultHttpMessageResponse<Long>> create(TransacaoRequest clienteRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                DefaultHttpMessageResponse.<Long>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK.value())
                        .data(service.createTransacao(clienteRequest))
                        .build()
        );
    }


    @Override
    public ResponseEntity<DefaultHttpMessageResponse<List<TransacaoResponse>>> findAllTransacaoByIdConta(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                DefaultHttpMessageResponse.<List<TransacaoResponse>>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK.value())
                        .data(service.findAllTransacoesByContaId(id))
                        .build()
        );
    }


    @Override
    public ResponseEntity<DefaultHttpMessageResponse<Transacao>> findTransacaoById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                DefaultHttpMessageResponse.<Transacao>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK.value())
                        .data(service.findTransacaoById(id))
                        .build()
        );
    }


}
