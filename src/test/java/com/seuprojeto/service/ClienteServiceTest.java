
package com.seuprojeto.service;

import com.seuprojeto.model.Cliente;
import com.seuprojeto.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Collections;

public class ClienteServiceTest {

    @Test
    void listarVazio() {
        ClienteRepository repo = Mockito.mock(ClienteRepository.class);
        Mockito.when(repo.findAll()).thenReturn(Collections.emptyList());
        ClienteService service = new ClienteService(repo);
        var lista = service.listar();
        assertNotNull(lista);
        assertEquals(0, lista.size());
    }
}
