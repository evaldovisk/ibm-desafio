package io.github.evaldo.ibm.Utils;

import io.github.evaldo.ibm.dto.cliente.ClienteRequest;
import io.github.evaldo.ibm.dto.cliente.ClienteResponse;
import io.github.evaldo.ibm.dto.transacao.TransacaoRequest;
import io.github.evaldo.ibm.dto.transacao.TransacaoResponse;
import io.github.evaldo.ibm.model.Cliente;
import io.github.evaldo.ibm.model.Conta;
import io.github.evaldo.ibm.model.Transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class FactoryUtils {

    private static final  String STRING_TESTE = "TESTE";
    private static final  Long LONG_TESTE = 1L;
    private static final  Integer INTEGER_TESTE = 1;

    private static final Double DOUBLE_TESTE = 1.5;
    private static final BigDecimal BIG_DECIMAL_TESTE = new BigDecimal(1);
    private static final  LocalDateTime LOCAL_DATE_TIME_TESTE = LocalDateTime.of(
            2024, 11, 7, 12, 0, 0, 0);;
    private static final Date DATE_TESTE = new Date(2024 - 1900, 10, 7);


    public static Cliente clienteMock(){
        return Cliente.builder()
                .id(LONG_TESTE)
                .nome(STRING_TESTE)
                .email(STRING_TESTE)
                .conta(FactoryUtils.contaMock()
                ).build();
    }

    public static Conta contaMock(){
        return Conta.builder()
                .id(LONG_TESTE)
                .numeroConta(STRING_TESTE)
                .dataCriacao(LOCAL_DATE_TIME_TESTE)
                .saldo(DOUBLE_TESTE)
                .build();
    }

    public static Transacao transacaoMock(){
        return Transacao.builder()
                .id(LONG_TESTE)
                .origem(FactoryUtils.contaMock())
                .destino(FactoryUtils.contaMock())
                .tipo(STRING_TESTE)
                .valor(DOUBLE_TESTE)
                .dataTransacao(DATE_TESTE)
                .build();
    }

    public static ClienteRequest clienteRequestMock(){
        return ClienteRequest.builder()
                .nome(STRING_TESTE)
                .idade(INTEGER_TESTE)
                .email(STRING_TESTE)
                .numeroConta(STRING_TESTE)
                .build();
    }

    public static ClienteResponse clienteResponseMock(){
        return ClienteResponse.builder()
                .id(LONG_TESTE)
                .nome(STRING_TESTE)
                .idade(INTEGER_TESTE)
                .email(STRING_TESTE)
                .conta(FactoryUtils.contaMock())
                .numeroConta(STRING_TESTE)
                .build();
    }

    public static TransacaoRequest transacaoRequestMock(){
        return TransacaoRequest.builder()
                .origem(INTEGER_TESTE)
                .destino(INTEGER_TESTE)
                .valor(BIG_DECIMAL_TESTE)
                .build();
    }

    public static TransacaoResponse transacaoResponseMock(){
        return TransacaoResponse.builder()
                .idTransacao(LONG_TESTE)
                .transacao(STRING_TESTE)
                .tipo(STRING_TESTE)
                .nomeDestino(STRING_TESTE)
                .conta(STRING_TESTE)
                .valor(DOUBLE_TESTE)
                .dataTransacao(STRING_TESTE)
                .build();

    }
}
