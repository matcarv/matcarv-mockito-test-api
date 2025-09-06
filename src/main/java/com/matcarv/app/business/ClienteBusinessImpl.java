package com.matcarv.app.business;

import com.matcarv.app.entities.Cliente;
import com.matcarv.app.enums.TransactionType;
import com.matcarv.app.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import java.util.UUID;
import lombok.Getter;

/**
 * Business implementation for Cliente entity.
 */
@Component
@Transactional(propagation = Propagation.SUPPORTS)
public class ClienteBusinessImpl implements ClienteBusiness {

	/* Repository for Cliente entity */
	@Getter
	@Autowired
	private ClienteRepository clienteRepository;

	/* Insert or update Cliente
	 *
	 * @param cliente the Cliente entity to insert or update
	 * @param transactionType the type of transaction (INSERT or UPDATE)
	 * @return the inserted or updated Cliente entity
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
		return getClienteRepository().save(cliente);
	}

	/* Delete Cliente by ID
	 *
	 * @param id the ID of the Cliente entity to delete
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteById(final UUID id) {
		getClienteRepository().deleteById(id);
	}

	/* Find Cliente by ID
	 *
	 * @param id the ID of the Cliente entity to find
	 * @return the found Cliente entity
	*/
	@Override
	public Cliente findById(final UUID id) {
		return getClienteRepository().findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Cliente not found with id: " + id));
	}

	/* Find Cliente by CPF
	 *
	 * @param cpf the CPF of the Cliente
	 * @return the found Cliente entity
	 */
	@Override
	public Cliente findByCpf(final String cpf) {
		return getClienteRepository().findByCpf(cpf)
			.orElseThrow(() -> new EntityNotFoundException("Cliente not found with CPF: " + cpf));
	}
}