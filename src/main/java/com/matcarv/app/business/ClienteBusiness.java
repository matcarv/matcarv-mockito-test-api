package com.matcarv.app.business;

import com.matcarv.app.entities.Cliente;
import com.matcarv.app.enums.TransactionType;
import java.util.UUID;

/*
 * Business interface for Cliente entity.
 */
public interface ClienteBusiness {

    /* Insert or update Cliente
     *
     * @param cliente the Cliente entity to insert or update
     * @param transactionType the type of transaction (INSERT or UPDATE)
     * @return the inserted or updated Cliente entity
     */
    public Cliente insertOrUpdate(final Cliente cliente, final TransactionType transactionType);

    /* Delete Cliente by ID
     *
     * @param id the ID of the Cliente entity to delete
     */
    public void deleteById(final UUID id);

    /* Find Cliente by ID
     *
     * @param id the ID of the Cliente entity to find
     * @return the found Cliente entity
     */
    public Cliente findById(final UUID id);

    /* Find Cliente by CPF
     *
     * @param cpf the CPF of the Cliente
     * @return the found Cliente entity
     */
    public Cliente findByCpf(String cpf);

}
