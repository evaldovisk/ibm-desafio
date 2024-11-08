package io.github.evaldo.ibm.Controller;


import io.github.evaldo.ibm.controller.implementation.ClienteControllerImpl;
import io.github.evaldo.ibm.dto.DefaultHttpMessageResponse;
import io.github.evaldo.ibm.dto.cliente.ClienteRequest;
import io.github.evaldo.ibm.dto.cliente.ClienteResponse;
import io.github.evaldo.ibm.service.ClienteService;
import io.github.evaldo.ibm.Utils.FactoryUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ClienteControllerImpTest {
    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteControllerImpl clienteController;

    private ClienteRequest clienteRequestMock;
    private ClienteResponse clienteResponseMock;
    private List<ClienteResponse> clienteResponseListMock;

    @BeforeEach
    void setUp() {
        clienteRequestMock = FactoryUtils.clienteRequestMock();
        clienteResponseMock = FactoryUtils.clienteResponseMock();
        clienteResponseListMock = List.of(clienteResponseMock);
    }

    @Test
    void testFindAll() {
        when(clienteService.findAll()).thenReturn(clienteResponseListMock);
        ResponseEntity<DefaultHttpMessageResponse<List<ClienteResponse>>> response = clienteController.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getBody().getStatus());
        assertNotNull(response.getBody().getData());
        assertEquals(1, response.getBody().getData().size());
        assertEquals("TESTE", response.getBody().getData().get(0).getNome());
    }

    @Test
    void testFindById() {
        Long clienteId = 1L;
        when(clienteService.findById(clienteId)).thenReturn(clienteResponseMock);
        ResponseEntity<DefaultHttpMessageResponse<ClienteResponse>> response = clienteController.findById(clienteId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getBody().getStatus());
        assertNotNull(response.getBody().getData());
        assertEquals("TESTE", response.getBody().getData().getNome());
    }

    @Test
    void testCreate() {
        when(clienteService.create(clienteRequestMock)).thenReturn(clienteResponseMock);
        ResponseEntity<DefaultHttpMessageResponse<ClienteResponse>> response = clienteController.create(clienteRequestMock);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getBody().getStatus());
        assertNotNull(response.getBody().getData());
        assertEquals("TESTE", response.getBody().getData().getNome());
    }
}



