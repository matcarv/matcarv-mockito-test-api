package com.matcarv.app.business;

import com.matcarv.app.entities.Cliente;
import com.matcarv.app.enums.TransactionType;
import com.matcarv.app.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/* Test class for ClienteBusinessImpl */
class ClienteBusinessImplTest {

    /* Repository for Cliente entity */
    @Mock
    private ClienteRepository clienteRepository;

    /* Business implementation for Cliente entity */
    @InjectMocks
    private ClienteBusinessImpl clienteBusiness;

    /* Initialization */
    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    /* Test findById method */
    @Test
    void testFindById() {
        final Cliente entity = createCliente();
        when(clienteRepository.findById(entity.getId())).thenReturn(Optional.of(entity));

        final Cliente result = clienteBusiness.findById(entity.getId());
        
        assertNotNull(result);
        assertEquals(entity.getId(), result.getId());
    }

    /* Test deleteById method */
    @Test
    void testDeleteById() {
        final UUID id = UUID.randomUUID();

        doNothing().when(clienteRepository).deleteById(id);

        assertDoesNotThrow(() -> clienteBusiness.deleteById(id));
        verify(clienteRepository, times(1)).deleteById(id);
    }

    /* Test insertOrUpdate method for INSERT operation */
    @Test
    void testInsertOrUpdateInsert() {
        final Cliente entity = createCliente();
        when(clienteRepository.save(entity)).thenReturn(entity);
        
        final Cliente result = clienteBusiness.insertOrUpdate(entity, TransactionType.INSERT);
        
        assertNotNull(result);
        verify(clienteRepository, times(1)).save(entity);
    }

    /* Test insertOrUpdate method for UPDATE operation */
    @Test
    void testInsertOrUpdateUpdate() {
        final Cliente entity = createCliente();

        when(clienteRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(clienteRepository.save(entity)).thenReturn(entity);

        final Cliente result = clienteBusiness.insertOrUpdate(entity, TransactionType.UPDATE);

        assertNotNull(result);
        assertEquals(entity.getId(), result.getId());
        verify(clienteRepository, times(1)).findById(entity.getId());
        verify(clienteRepository, times(1)).save(entity);
    }

    /* Test findByCpf method */
    @Test
    void testFindByCpf() {
        final Cliente entity = createCliente();
        final String cpf = "12345678901";

        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.of(entity));

        final Cliente result = clienteBusiness.findByCpf(cpf);

        assertNotNull(result);
        assertEquals(cpf, result.getCpf());
        verify(clienteRepository, times(1)).findByCpf(cpf);
    }

    /* Helper method to create a Cliente entity */
    private Cliente createCliente() {
        final Cliente cliente = new Cliente();
        cliente.setId(UUID.randomUUID());
        cliente.setCpf("12345678901");
        return cliente;
    }
}
