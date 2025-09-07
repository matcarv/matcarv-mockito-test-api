package com.matcarv.app.repository;

import java.util.Optional;
import java.util.UUID;

import com.matcarv.app.entities.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repository interface for Vendedor entity.
 * 
 * @author Weslley Matos
 */
public interface VendedorRepository extends JpaRepository<Vendedor, UUID>, JpaSpecificationExecutor<Vendedor> {

    /**
     * Find a Vendedor by its CPF.
     *
     * @param cpf the CPF of the Vendedor
     * @return the found Vendedor entity
     */
    public Optional<Vendedor> findByCpf(String cpf);
}
