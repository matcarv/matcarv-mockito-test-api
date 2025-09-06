package com.matcarv.app.resources;

import com.matcarv.app.business.ClienteBusiness;
import com.matcarv.app.converters.ClienteConverter;
import com.matcarv.app.dtos.ClienteDTO;
import com.matcarv.app.entities.Cliente;
import com.matcarv.app.enums.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/* Testes unitários para o recurso Cliente */
public class ClienteResourceTest {

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
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    /* Teste de inserção */
    @Test
    public void testInsert() {
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
    public void testUpdate() {
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
    public void testDelete() {
        final UUID id = UUID.randomUUID();
        doNothing().when(clienteBusiness).deleteById(id);

        final ResponseEntity<?> response = clienteResource.delete(id);
        
        assertEquals(200, response.getStatusCode().value());
    }

    /* Teste de busca por ID */
    @Test
    public void testGetClienteById() {
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
    public void testGetClienteByCpf() {
        final String cpf = "12345678901";
        final Cliente entity = new Cliente();
        final ClienteDTO dto = new ClienteDTO();

        when(clienteBusiness.findByCpf(cpf)).thenReturn(entity);
        when(clienteConverter.toDTO(entity)).thenReturn(dto);

        final ResponseEntity<?> response = clienteResource.getClienteByCpf(cpf);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(dto, response.getBody());
    }
}
