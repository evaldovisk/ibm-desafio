package io.github.evaldo.ibm.service.implementation;
import io.github.evaldo.ibm.dto.cliente.ClienteRequest;
import io.github.evaldo.ibm.dto.cliente.ClienteResponse;
import io.github.evaldo.ibm.exceptions.BadRequestException;
import io.github.evaldo.ibm.exceptions.ObjectExistingException;
import io.github.evaldo.ibm.exceptions.ObjectNotFoundException;
import io.github.evaldo.ibm.model.Cliente;
import io.github.evaldo.ibm.model.Conta;
import io.github.evaldo.ibm.repository.ClienteRepository;
import io.github.evaldo.ibm.repository.ContaRepository;
import io.github.evaldo.ibm.service.ClienteService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;
    private final ContaRepository contaRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ContaRepository contaRepository) {
        this.clienteRepository = clienteRepository;
        this.contaRepository = contaRepository;
    }

    @Override
    @Transactional
    public List<ClienteResponse> findAll() {
        try {
            return clienteRepository.findAll()
                    .stream()
                    .map(cliente -> ClienteResponse.builder()
                            .id(cliente.getId())
                            .nome(cliente.getNome())
                            .idade(cliente.getIdade())
                            .email(cliente.getEmail())
                            .conta(cliente.getConta())
                            .numeroConta(getNumeroConta(cliente))
                            .build())
                    .toList();
        } catch (Exception e) {
            throw new BadRequestException("Erro inesperado ao buscar clientes: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public ClienteResponse findById(Long id) {
        try {
            return clienteRepository.findById(id)
                    .map(cliente -> ClienteResponse.builder()
                            .id(cliente.getId())
                            .nome(cliente.getNome())
                            .idade(cliente.getIdade())
                            .email(cliente.getEmail())
                            .conta(cliente.getConta())
                            .numeroConta(getNumeroConta(cliente))
                            .build())
                    .orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
        } catch (ObjectNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new BadRequestException("Erro inesperado ao buscar cliente: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public ClienteResponse create(ClienteRequest clienteRequest) {
        try {
            if (clienteRepository.findByEmailAndNumeroConta(clienteRequest.getEmail(), clienteRequest.getNumeroConta()).isPresent()) {
                throw new ObjectExistingException("Cliente já existe");
            }

            Cliente cliente = Cliente.builder()
                    .nome(clienteRequest.getNome())
                    .idade(clienteRequest.getIdade())
                    .email(clienteRequest.getEmail())
                    .build();
            clienteRepository.save(cliente);

            Conta conta = Conta.builder()
                    .numeroConta(clienteRequest.getNumeroConta())
                    .saldo(0.0)
                    .cliente(cliente)
                    .build();
                contaRepository.save(conta);

            cliente.setConta(conta);

            return ClienteResponse.builder()
                    .id(cliente.getId())
                    .nome(cliente.getNome())
                    .idade(cliente.getIdade())
                    .email(cliente.getEmail())
                    .conta(cliente.getConta())
                    .numeroConta(conta.getNumeroConta())
                    .build();
        } catch (ObjectExistingException e) {
            throw e;
        } catch (Exception e) {
            throw new BadRequestException("Erro inesperado ao criar cliente: " + e.getMessage(), e);
        }
    }

    private String getNumeroConta(Cliente cliente) {
        try {
            return contaRepository.findById(cliente.getConta().getId())
                    .map(conta -> conta.getNumeroConta())
                    .orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada"));
        } catch (Exception e) {
            throw new BadRequestException("Erro inesperado ao buscar número da conta: " + e.getMessage(), e);
        }
    }
}
