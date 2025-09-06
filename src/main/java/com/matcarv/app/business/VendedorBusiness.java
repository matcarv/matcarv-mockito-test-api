package com.matcarv.app.business;

import com.matcarv.app.entities.Vendedor;
import com.matcarv.app.enums.TransactionType;
import java.util.UUID;

/*
 * Business interface for Vendedor entity.
 */
public interface VendedorBusiness {

    /* Insert or update Vendedor
     *
     * @param vendedor the Vendedor entity to insert or update
     * @param transactionType the type of transaction (INSERT or UPDATE)
     * @return the inserted or updated Vendedor entity
     */
    public Vendedor insertOrUpdate(final Vendedor vendedor, final TransactionType transactionType);

    /* Delete Vendedor by ID
     *
     * @param id the ID of the Vendedor entity to delete
     */
    public void deleteById(final UUID id);

    /* Find Vendedor by ID
     *
     * @param id the ID of the Vendedor entity to find
     * @return the found Vendedor entity
     */
    public Vendedor findById(final UUID id);

    /*
     * Find a Vendedor by its CPF.
     *
     * @param cpf the CPF of the Vendedor
     * @return the found Vendedor entity
     */
    public Vendedor findByCpf(String cpf);
}
