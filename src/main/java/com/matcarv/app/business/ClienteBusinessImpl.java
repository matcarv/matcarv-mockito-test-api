package com.matcarv.app.business;

import com.matcarv.app.entities.Cliente;
import com.matcarv.app.enums.TransactionType;
import com.matcarv.app.repository.ClienteRepository;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import java.util.UUID;

/**
 * Implementação das regras de negócio para Cliente.
 *
 * @author Weslley Matos
 */
@Component
@Transactional(propagation = Propagation.SUPPORTS)
public class ClienteBusinessImpl implements ClienteBusiness {

	/**
	 * Repositório para Cliente.
	 */
	@Getter
	private final ClienteRepository clienteRepository;

	/**
	 * Injeção de dependência via construtor.
	 * 
	 * @param clienteRepository repositório de Cliente
	 */
	public ClienteBusinessImpl(final ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	/**
	 * Insere ou atualiza um cliente.
	 *
	 * @param cliente Cliente a ser inserido ou atualizado
	 * @param transactionType Tipo de transação (INSERT ou UPDATE)
	 * @return Cliente inserido ou atualizado
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Cliente insertOrUpdate(final Cliente cliente, final TransactionType transactionType) {
		switch (transactionType) {
			case UPDATE:
				final Cliente existingCliente = findById(cliente.getId());
				if (existingCliente == null) {
					throw new EntityNotFoundException("Cliente not found with id: " + cliente.getId());
				}
				BeanUtils.copyProperties(cliente, existingCliente, "id", "dataCriacao");
				break;
			default:
				break;
		}
		return clienteRepository.save(cliente);
	}

	/**
	 * Remove um cliente pelo ID.
	 *
	 * @param id ID do cliente a ser removido
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteById(final UUID id) {
		clienteRepository.deleteById(id);
	}

	/**
	 * Busca um cliente pelo ID.
	 *
	 * @param id ID do cliente
	 * @return Cliente encontrado
	 */
	@Override
	public Cliente findById(final UUID id) {
		return clienteRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Cliente not found with id: " + id));
	}

	/**
	 * Busca um cliente pelo CPF.
	 *
	 * @param cpf CPF do cliente
	 * @return Cliente encontrado
	 */
	@Override
	public Cliente findByCpf(final String cpf) {
		return clienteRepository.findByCpf(cpf)
			.orElseThrow(() -> new EntityNotFoundException("Cliente not found with CPF: " + cpf));
	}
}