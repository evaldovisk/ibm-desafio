package io.github.evaldo.ibm.service;

import io.github.evaldo.ibm.dto.cliente.ClienteRequest;
import io.github.evaldo.ibm.dto.cliente.ClienteResponse;
import io.github.evaldo.ibm.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface ClienteService {

  List<ClienteResponse> findAll();

  ClienteResponse findById(Long id);

  ClienteResponse create(ClienteRequest clienteRequest);
}
