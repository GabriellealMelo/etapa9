
package com.seuprojeto.service;

import com.seuprojeto.dto.ClienteRequest;
import com.seuprojeto.dto.ClienteResponse;
import com.seuprojeto.model.Cliente;
import com.seuprojeto.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<ClienteResponse> listar() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ClienteResponse salvar(ClienteRequest req) {
        if (repository.existsByCpf(req.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        Cliente c = Cliente.builder()
                .nome(req.getNome())
                .cpf(req.getCpf())
                .email(req.getEmail())
                .telefone(req.getTelefone())
                .build();
        Cliente saved = repository.save(c);
        return toResponse(saved);
    }

    public ClienteResponse buscarPorId(Long id) {
        return repository.findById(id).map(this::toResponse).orElse(null);
    }

    public ClienteResponse atualizar(Long id, ClienteRequest req) {
        Cliente existente = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        // if cpf changed, check uniqueness
        if (!existente.getCpf().equals(req.getCpf()) && repository.existsByCpf(req.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        existente.setNome(req.getNome());
        existente.setCpf(req.getCpf());
        existente.setEmail(req.getEmail());
        existente.setTelefone(req.getTelefone());
        Cliente saved = repository.save(existente);
        return toResponse(saved);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    private ClienteResponse toResponse(Cliente c) {
        return ClienteResponse.builder()
                .id(c.getId())
                .nome(c.getNome())
                .cpf(c.getCpf())
                .email(c.getEmail())
                .telefone(c.getTelefone())
                .build();
    }
}
