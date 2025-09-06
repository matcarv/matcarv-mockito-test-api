package com.matcarv.app.business;

import com.matcarv.app.entities.Vendedor;
import com.matcarv.app.enums.TransactionType;
import com.matcarv.app.repository.VendedorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import java.util.UUID;
import lombok.Getter;

/**
 * Business implementation for Vendedor entity.
 * 
 * @author Weslley Matos
 */
@Component
@Transactional(propagation = Propagation.SUPPORTS)
public class VendedorBusinessImpl implements VendedorBusiness {

    /* Repository for Vendedor entity */
    @Getter
    private final VendedorRepository vendedorRepository;

    /**
     * Injeção de dependência via construtor.
     * 
     * @param vendedorRepository repositório de Vendedor
     */
    public VendedorBusinessImpl(final VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    /** Insert or update Vendedor
     *
     * @param vendedor the Vendedor entity to insert or update
     * @param transactionType the type of transaction (INSERT or UPDATE)
     * @return the inserted or updated Vendedor entity
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Vendedor insertOrUpdate(final Vendedor vendedor, TransactionType transactionType) {
        switch (transactionType) {
            case UPDATE:
                final Vendedor existingVendedor = findById(vendedor.getId());
                if (existingVendedor == null) {
                    throw new EntityNotFoundException("Vendedor not found with id: " + vendedor.getId());
                }

                BeanUtils.copyProperties(vendedor, existingVendedor, "id", "dataCriacao");
                break;
            default:
                break;
        }
        
        return getVendedorRepository().save(vendedor);
    }

    /** Delete Vendedor by ID
     *
     * @param id the ID of the Vendedor entity to delete
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(final UUID id) {
        getVendedorRepository().deleteById(id);
    }

    /** Find Vendedor by ID
     *
     * @param id the ID of the Vendedor entity to find
     * @return the found Vendedor entity
    */
    @Override
    public Vendedor findById(final UUID id) {
        return getVendedorRepository().findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Vendedor not found with id: " + id));
    }

    /** Find Vendedor by CPF
     *
     * @param cpf the CPF of the Vendedor
     * @return the found Vendedor entity
     */
    @Override
    public Vendedor findByCpf(final String cpf) {
        return getVendedorRepository().findByCpf(cpf)
            .orElseThrow(() -> new EntityNotFoundException("Vendedor not found with CPF: " + cpf));
    }

}
