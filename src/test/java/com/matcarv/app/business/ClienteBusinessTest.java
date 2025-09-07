package com.matcarv.app.business;

import com.matcarv.app.entities.Cliente;
import com.matcarv.app.enums.TransactionType;
import com.matcarv.app.repository.ClienteRepository;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import java.util.UUID;

import com.matcarv.app.dtos.ClienteSearchDTO;
import org.springframework.data.jpa.domain.Specification;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/* Test class for ClienteBusinessImpl */
class ClienteBusinessTest {

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

    /* Test findByFilter method */
    @Test
    void testFindByFilter() {
        final Cliente cliente1 = createCliente();
        cliente1.setNome("João");
        cliente1.setEmail("joao@email.com");

        when(clienteRepository.findAll(ArgumentMatchers.<Specification<Cliente>> any()))
            .thenReturn(List.of(cliente1));

        final List<ClienteSearchDTO> result = clienteBusiness.findByFilter(Map.of("nome", "João", "cpf", "98765432100"));

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("João", result.get(0).getNome());
        assertEquals("joao@email.com", result.get(0).getEmail());
        verify(clienteRepository, times(1)).findAll(any(Specification.class));
    }

     /* Test findByFilter method */
    @Test
    void testFindByFilterWithoutFilter() {
        final Cliente cliente1 = createCliente();
        cliente1.setNome("João");
        cliente1.setEmail("joao@email.com");

        final Cliente cliente2 = createCliente();
        cliente2.setNome("Maria");
        cliente2.setEmail("maria@email.com");

        when(clienteRepository.findAll(ArgumentMatchers.<Specification<Cliente>> any()))
            .thenReturn(List.of(cliente1, cliente2));

        // Call with an empty filter map to test behavior without filters
        final List<ClienteSearchDTO> result = clienteBusiness.findByFilter(anyMap());

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("João", result.get(0).getNome());
        assertEquals("Maria", result.get(1).getNome());
        assertEquals("joao@email.com", result.get(0).getEmail());
        assertEquals("maria@email.com", result.get(1).getEmail());
        verify(clienteRepository, times(1)).findAll(any(Specification.class));
    }

    /* Helper method to create a Cliente entity */
    private Cliente createCliente() {
        final Cliente cliente = new Cliente();
        cliente.setId(UUID.randomUUID());
        cliente.setCpf("12345678901");
        return cliente;
    }
}
