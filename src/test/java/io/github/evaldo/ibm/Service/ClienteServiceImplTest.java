package io.github.evaldo.ibm.Service;

import io.github.evaldo.ibm.dto.cliente.ClienteRequest;
import io.github.evaldo.ibm.dto.cliente.ClienteResponse;
import io.github.evaldo.ibm.exceptions.BadRequestException;
import io.github.evaldo.ibm.exceptions.ObjectExistingException;
import io.github.evaldo.ibm.exceptions.ObjectNotFoundException;
import io.github.evaldo.ibm.model.Cliente;
import io.github.evaldo.ibm.model.Conta;
import io.github.evaldo.ibm.repository.ClienteRepository;
import io.github.evaldo.ibm.repository.ContaRepository;
import io.github.evaldo.ibm.Utils.FactoryUtils;
import io.github.evaldo.ibm.service.implementation.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {
    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private ClienteRequest clienteRequestMock;
    private Cliente clienteMock;
    private Conta contaMock;
    private ClienteResponse clienteResponseMock;

    @BeforeEach
    void setUp() {
        clienteRequestMock = FactoryUtils.clienteRequestMock();
        clienteMock = FactoryUtils.clienteMock();
        contaMock = clienteMock.getConta();
        clienteResponseMock = FactoryUtils.clienteResponseMock();
    }

    @Test
    void testFindAll() {
        when(clienteRepository.findAll()).thenReturn(List.of(FactoryUtils.clienteMock()));
        when(contaRepository.findById(anyLong())).thenReturn(Optional.of(FactoryUtils.contaMock()));

        List<ClienteResponse> clienteResponses = clienteService.findAll();

        assertNotNull(clienteResponses);
        assertEquals(1, clienteResponses.size());
        assertEquals("TESTE", clienteResponses.get(0).getNome());


    }

    @Test
    void testFindById() {
        Long id = 1L;
        when(clienteRepository.findById(id)).thenReturn(Optional.of(FactoryUtils.clienteMock()));
        when(contaRepository.findById(anyLong())).thenReturn(Optional.of(FactoryUtils.contaMock()));

        ClienteResponse clienteResponse = clienteService.findById(id);

        assertNotNull(clienteResponse);
        assertEquals(id, clienteResponse.getId());
        assertEquals("TESTE", clienteResponse.getNome());
    }

    @Test
    void testFindById_ShouldThrowObjectNotFoundException() {
        Long id = 1L;
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> clienteService.findById(id));
    }

    @Test
    void testCreate_ShouldThrowObjectExistingException() {
        when(clienteRepository.findByEmailAndNumeroConta(clienteRequestMock.getEmail(), clienteRequestMock.getNumeroConta()))
                .thenReturn(Optional.of(clienteMock));

        assertThrows(ObjectExistingException.class, () -> clienteService.create(clienteRequestMock));
    }

    @Test
    void testCreate_ShouldThrowBadRequestException_WhenRepositoryFails() {
        when(clienteRepository.findByEmailAndNumeroConta(clienteRequestMock.getEmail(), clienteRequestMock.getNumeroConta()))
                .thenReturn(Optional.empty());

        when(clienteRepository.save(any(Cliente.class)))
                .thenThrow(new DataIntegrityViolationException("Erro ao salvar cliente"));

        assertThrows(BadRequestException.class, () -> clienteService.create(clienteRequestMock));
    }
}
