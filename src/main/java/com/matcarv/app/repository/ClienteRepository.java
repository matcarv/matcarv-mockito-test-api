package com.matcarv.app.repository;

import java.util.UUID;


import com.matcarv.app.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


/**
 * Repository interface for Cliente entity.
 */
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    /*
     * Find a Cliente by its CPF.
     * 
     * @param cpf the CPF of the Cliente
     * @return the found Cliente entity
     */
    public Optional<Cliente> findByCpf(String cpf);
}
