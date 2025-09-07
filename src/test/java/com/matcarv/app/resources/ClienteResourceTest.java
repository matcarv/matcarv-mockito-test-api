package com.matcarv.app.resources;

import com.matcarv.app.business.ClienteBusiness;
import com.matcarv.app.converters.ClienteConverter;
import com.matcarv.app.dtos.ClienteDTO;
import com.matcarv.app.dtos.ClienteSearchDTO;
import com.matcarv.app.dtos.VendedorSearchDTO;
import com.matcarv.app.entities.Cliente;
import com.matcarv.app.enums.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.*;

/* Testes unitários para o recurso Cliente */
class ClienteResourceTest {

    /* Mock do ClienteBusiness */
    @Mock
    private ClienteBusiness clienteBusiness;

    /* Mock do ClienteConverter */
    @Mock
    private ClienteConverter clienteConverter;

    /* Classe sob teste */
    @InjectMocks
    private ClienteResource clienteResource;

    /* Inicialização dos mocks */
    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    /* Teste de inserção */
    @Test
    void testInsert() {
        final ClienteDTO dto = new ClienteDTO();
        final Cliente entity = new Cliente();
        
        when(clienteConverter.toEntity(dto)).thenReturn(entity);
        when(clienteBusiness.insertOrUpdate(entity, TransactionType.INSERT)).thenReturn(entity);
        when(clienteConverter.toDTO(entity)).thenReturn(dto);

        final ResponseEntity<?> response = clienteResource.insert(dto);
        assertEquals(201, response.getStatusCode().value());
        assertEquals(dto, response.getBody());
    }

    /* Teste de atualização */
    @Test
    void testUpdate() {
        final ClienteDTO dto = new ClienteDTO();
        final Cliente entity = new Cliente();
        
        when(clienteConverter.toEntity(dto)).thenReturn(entity);
        when(clienteBusiness.insertOrUpdate(entity, TransactionType.UPDATE)).thenReturn(entity);
        when(clienteConverter.toDTO(entity)).thenReturn(dto);

        final ResponseEntity<?> response = clienteResource.update(dto);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(dto, response.getBody());
    }

    /* Teste de deleção */
    @Test
    void testDelete() {
        final UUID id = UUID.randomUUID();
        doNothing().when(clienteBusiness).deleteById(id);

        final ResponseEntity<?> response = clienteResource.delete(id);
        
        assertEquals(200, response.getStatusCode().value());
    }

    /* Teste de busca por ID */
    @Test
    void testGetClienteById() {
        final UUID id = UUID.randomUUID();
        final Cliente entity = new Cliente();
        final ClienteDTO dto = new ClienteDTO();
        
        when(clienteBusiness.findById(id)).thenReturn(entity);
        when(clienteConverter.toDTO(entity)).thenReturn(dto);
        
        final ResponseEntity<?> response = clienteResource.getClienteById(id);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(dto, response.getBody());
    }
    /* Teste de busca por CPF */
    @Test
    void testGetClienteByCpf() {
        final String cpf = "12345678901";
        final Cliente entity = new Cliente();
        final ClienteDTO dto = new ClienteDTO();

        when(clienteBusiness.findByCpf(cpf)).thenReturn(entity);
        when(clienteConverter.toDTO(entity)).thenReturn(dto);

        final ResponseEntity<?> response = clienteResource.getClienteByCpf(cpf);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(dto, response.getBody());
    }

        /* Teste de busca por filtros */
    @Test
    void testSearchClientes() {
        final Map<String, String> filters = Map.of("nome", "João", "cpf", "12345678901");

        final ClienteSearchDTO cliente1 = new ClienteSearchDTO(UUID.randomUUID(), "João", "12345678901", "email@email.com");
        final ClienteSearchDTO cliente2 = new ClienteSearchDTO(UUID.randomUUID(), "Maria", "10987654321", "email2@email.com");

        when(clienteBusiness.findByFilter(anyMap())).thenReturn(List.of(cliente1, cliente2));

        final ResponseEntity<List<ClienteSearchDTO>> response = clienteResource.getClienteByFilter(filters);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(List.of(cliente1, cliente2), response.getBody());
        verify(clienteBusiness, times(1)).findByFilter(anyMap());
    }
}
