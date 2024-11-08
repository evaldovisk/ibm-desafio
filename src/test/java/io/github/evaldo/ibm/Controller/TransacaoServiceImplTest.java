package io.github.evaldo.ibm.Controller;


import io.github.evaldo.ibm.controller.implementation.TransacaoControllerImpl;
import io.github.evaldo.ibm.dto.DefaultHttpMessageResponse;
import io.github.evaldo.ibm.dto.transacao.TransacaoRequest;
import io.github.evaldo.ibm.dto.transacao.TransacaoResponse;
import io.github.evaldo.ibm.model.Transacao;
import io.github.evaldo.ibm.service.TransacaoService;
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
public class TransacaoServiceImplTest {

    @Mock
    private TransacaoService transacaoService;

    @InjectMocks
    private TransacaoControllerImpl transacaoController;

    private TransacaoRequest transacaoRequestMock;
    private TransacaoResponse transacaoResponseMock;
    private List<TransacaoResponse> transacaoResponseListMock;
    private Transacao transacaoMock;

    @BeforeEach
    void setUp() {
        transacaoRequestMock = FactoryUtils.transacaoRequestMock();
        transacaoResponseMock = FactoryUtils.transacaoResponseMock();
        transacaoResponseListMock = List.of(transacaoResponseMock);
        transacaoMock = FactoryUtils.transacaoMock();
    }

    @Test
    void testCreate() {
        when(transacaoService.createTransacao(transacaoRequestMock)).thenReturn(1L);

        ResponseEntity<DefaultHttpMessageResponse<Long>> response = transacaoController.create(transacaoRequestMock);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getBody().getStatus());
        assertEquals(1L, response.getBody().getData());
    }

    @Test
    void testFindAllTransacaoByIdConta() {
        Long idConta = 1L;
        when(transacaoService.findAllTransacoesByContaId(idConta)).thenReturn(transacaoResponseListMock);

        ResponseEntity<DefaultHttpMessageResponse<List<TransacaoResponse>>> response = transacaoController.findAllTransacaoByIdConta(idConta);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getBody().getStatus());
        assertNotNull(response.getBody().getData());
        assertEquals(1, response.getBody().getData().size());
        assertEquals("TESTE", response.getBody().getData().get(0).getTransacao());
    }

    @Test
    void testFindTransacaoById() {
        Long transacaoId = 1L;
        when(transacaoService.findTransacaoById(transacaoId)).thenReturn(transacaoMock);

        ResponseEntity<DefaultHttpMessageResponse<Transacao>> response = transacaoController.findTransacaoById(transacaoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getBody().getStatus());
        assertNotNull(response.getBody().getData());
        assertEquals(1L, response.getBody().getData().getId());
    }
}
