package io.github.evaldo.ibm.controller;

import io.github.evaldo.ibm.dto.DefaultHttpMessageResponse;
import io.github.evaldo.ibm.dto.cliente.ClienteRequest;
import io.github.evaldo.ibm.dto.cliente.ClienteResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cliente")
public interface ClienteController {
    @Operation(
            summary = "Obtem todos os clientes junto a conta",
            description = "Resposta padrão encapsulando o uma lista de ClienteResponse",
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
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<DefaultHttpMessageResponse<List<ClienteResponse>>> findAll();

    @Operation(
            summary = "Obtem um junto a conta pelo Id.",
            description = "Resposta padrão encapsulando o ClienteResponse.",
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
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<DefaultHttpMessageResponse<ClienteResponse>> findById(
            @PathVariable Long id);


    @Operation(
            summary = "Inseri um cliente junto a conta via ClienteRequest.",
            description = "Resposta padrão encapsulando o ClienteResponse que foi inserido.",
            responses = {
                    @ApiResponse(
                            description = "Requisição executada com sucesso.",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = DefaultHttpMessageResponse.class))
                    ),
                    @ApiResponse(
                            description = "Requisição inválida.",
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = DefaultHttpMessageResponse.class))
                    ),
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<DefaultHttpMessageResponse<ClienteResponse>> create(@RequestBody ClienteRequest clienteRequest);

}
