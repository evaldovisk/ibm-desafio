package io.github.evaldo.ibm.Service;

import io.github.evaldo.ibm.Utils.FactoryUtils;
import io.github.evaldo.ibm.dto.transacao.TransacaoRequest;
import io.github.evaldo.ibm.dto.transacao.TransacaoResponse;
import io.github.evaldo.ibm.exceptions.BadRequestException;
import io.github.evaldo.ibm.exceptions.ObjectNotFoundException;
import io.github.evaldo.ibm.model.Cliente;
import io.github.evaldo.ibm.model.Conta;
import io.github.evaldo.ibm.model.Transacao;
import io.github.evaldo.ibm.repository.TransacaoRepository;
import io.github.evaldo.ibm.service.implementation.TransacaoServiceImpl;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceImplTest {

    @Mock
    private TransacaoRepository transacaoRepository;

    @InjectMocks
    private TransacaoServiceImpl transacaoService;

    private Transacao transacaoMock;

    @BeforeEach
    void setUp() {
        transacaoMock = new Transacao();
    }

    @Test
    void testFindAllTransacoesByContaId() {
        Transacao transacaoMock = FactoryUtils.transacaoMock();

        Conta contaOrigem = new Conta();
        Cliente clienteOrigem = new Cliente();
        clienteOrigem.setNome("Cliente Origem");
        contaOrigem.setCliente(clienteOrigem);
        transacaoMock.setOrigem(contaOrigem);

        Conta contaDestino = new Conta();
        Cliente clienteDestino = new Cliente();
        clienteDestino.setNome("Cliente Destino");
        contaDestino.setCliente(clienteDestino);
        transacaoMock.setDestino(contaDestino);

        when(transacaoRepository.findAllByIdOrigemOrIdDestino(anyLong())).thenReturn(List.of(transacaoMock));

        List<TransacaoResponse> response = transacaoService.findAllTransacoesByContaId(1L);

        assertNotNull(response);
        assertFalse(response.isEmpty());

        assertEquals("Entrada", response.get(0).getTransacao());
        assertEquals("Transferência", response.get(0).getTipo());
        assertEquals("Cliente Origem", response.get(0).getNomeDestino());
    }

    @Test
    void testCreateTransacao_ShouldReturnTransacaoId() {
        when(transacaoRepository.realizarTransferencia(anyInt(), anyInt(), any())).thenReturn(1L);

        Long result = transacaoService.createTransacao(FactoryUtils.transacaoRequestMock());

        assertNotNull(result);
        assertEquals(1L, result);
    }

    @Test
    void testFindTransacaoById_ShouldReturnTransacao() {
        when(transacaoRepository.findById(anyLong())).thenReturn(Optional.of(transacaoMock));

        Transacao result = transacaoService.findTransacaoById(1L);

        assertNotNull(result);
        assertEquals(transacaoMock, result);
    }

    @Test
    void testFindTransacaoById_ShouldThrowObjectNotFoundException_WhenTransacaoNotFound() {
        when(transacaoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> transacaoService.findTransacaoById(1L));
    }

    @Test
    void testFindAllTransacoesByContaId_ShouldThrowBadRequestException_WhenRepositoryFails() {
        when(transacaoRepository.findAllByIdOrigemOrIdDestino(anyLong())).thenThrow(new DataIntegrityViolationException("Erro de banco de dados"));

        assertThrows(BadRequestException.class, () -> transacaoService.findAllTransacoesByContaId(1L));
    }
}
