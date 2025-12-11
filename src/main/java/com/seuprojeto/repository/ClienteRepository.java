
package com.seuprojeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.seuprojeto.model.Cliente;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
}
