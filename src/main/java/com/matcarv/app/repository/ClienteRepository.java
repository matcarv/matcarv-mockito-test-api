package com.matcarv.app.repository;

import java.util.UUID;

import com.matcarv.app.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;

/**
 * Repository interface for Cliente entity.
 * 
 * @author Weslley Matos
 */
public interface ClienteRepository extends JpaRepository<Cliente, UUID>, JpaSpecificationExecutor<Cliente> {

    /**
     * Find a Cliente by its CPF.
     * 
     * @param cpf the CPF of the Cliente
     * @return the found Cliente entity
     */
    public Optional<Cliente> findByCpf(final String cpf);

}
