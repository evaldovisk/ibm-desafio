package io.github.evaldo.ibm.controller;


import io.github.evaldo.ibm.dto.DefaultHttpMessageResponse;
import io.github.evaldo.ibm.dto.transacao.TransacaoRequest;
import io.github.evaldo.ibm.dto.transacao.TransacaoResponse;
import io.github.evaldo.ibm.model.Transacao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/transacao")
public interface TransacaoController {

    @Operation(
            summary = "Realização transção entre duas contas utilizando TransacaoRequest",
            description = "Utiliza de uma Procedure para realizar a inserção via id das Contas",
            responses = {
                    @ApiResponse(
                            description = "Requisição executada com sucesso.",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Requisição inválida.",
                            responseCode = "400",
                            content = @Content(schema = @Schema())
                    ),
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<DefaultHttpMessageResponse<Long>> create(@RequestBody TransacaoRequest transacaoRequest);

    @Operation(
            summary = "Obtem todas as transações de um Cliente via Id da Conta",
            description = "Resposta padrão encapsulando o TransacaoResponse.",
            responses = {
                    @ApiResponse(
                            description = "Requisição executada com sucesso.",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = DefaultHttpMessageResponse.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Requisição inválida.",
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = DefaultHttpMessageResponse.class))
                    ),
            }
    )
    @GetMapping(value ="/all/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<DefaultHttpMessageResponse<List<TransacaoResponse>>> findAllTransacaoByIdConta(@PathVariable Long id);


    @Operation(
            summary = "Obtem uma transação pelo id da Transação.",
            description = "Resposta padrão encapsulando um objeto Transacao.",
            responses = {
                    @ApiResponse(
                            description = "Requisição executada com sucesso.",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = DefaultHttpMessageResponse.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Requisição inválida.",
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = DefaultHttpMessageResponse.class))
                    ),
            }
    )
    @GetMapping(value ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<DefaultHttpMessageResponse<Transacao>> findTransacaoById(@PathVariable Long id);

}
