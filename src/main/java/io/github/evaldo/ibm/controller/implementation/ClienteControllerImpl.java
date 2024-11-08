package io.github.evaldo.ibm.controller.implementation;

import io.github.evaldo.ibm.controller.ClienteController;
import io.github.evaldo.ibm.dto.DefaultHttpMessageResponse;
import io.github.evaldo.ibm.dto.cliente.ClienteRequest;
import io.github.evaldo.ibm.dto.cliente.ClienteResponse;
import io.github.evaldo.ibm.model.Cliente;
import io.github.evaldo.ibm.service.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ClienteControllerImpl implements ClienteController {

    private final ClienteService service;

    public ClienteControllerImpl(ClienteService service){
        this.service = service;
    }

    @Override
    public ResponseEntity<DefaultHttpMessageResponse<List<ClienteResponse>>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                DefaultHttpMessageResponse.<List<ClienteResponse>>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK.value())
                        .data(service.findAll())
                        .build()
        );
    }

    @Override
    public ResponseEntity<DefaultHttpMessageResponse<ClienteResponse>> findById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                DefaultHttpMessageResponse.<ClienteResponse>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK.value())
                        .data(service.findById(id))
                        .build()
        );
    }

    @Override
    public ResponseEntity<DefaultHttpMessageResponse<ClienteResponse>> create(ClienteRequest clienteRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                DefaultHttpMessageResponse.<ClienteResponse>builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK.value())
                        .data(service.create(clienteRequest))
                        .build()
        );
    }

}
