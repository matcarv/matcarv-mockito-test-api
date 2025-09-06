package com.matcarv.app.business;

import com.matcarv.app.entities.Cliente;
import com.matcarv.app.enums.TransactionType;
import java.util.UUID;

/**
 * Interface que define as operações de negócio para Cliente.
 *
 * @author Weslley Matos
 */
public interface ClienteBusiness {

    /**
     * Insere ou atualiza um cliente.
     *
     * @param cliente Cliente a ser inserido ou atualizado
     * @param transactionType Tipo de transação (INSERT ou UPDATE)
     * @return Cliente inserido ou atualizado
     */
    public Cliente insertOrUpdate(final Cliente cliente, final TransactionType transactionType);

    /**
     * Remove um cliente pelo ID.
     *
     * @param id ID do cliente a ser removido
     */
    public void deleteById(final UUID id);

    /**
     * Busca um cliente pelo ID.
     *
     * @param id ID do cliente
     * @return Cliente encontrado ou null se não existir
     */
    public Cliente findById(final UUID id);

    /**
     * Busca um cliente pelo CPF.
     *
     * @param cpf CPF do cliente
     * @return Cliente encontrado ou null se não existir
     */
    public Cliente findByCpf(String cpf);

}
