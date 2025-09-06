package com.matcarv.app.resources;

import com.matcarv.app.business.VendedorBusiness;
import com.matcarv.app.converters.VendedorConverter;
import com.matcarv.app.dtos.VendedorDTO;
import com.matcarv.app.entities.Vendedor;
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

/* Testes unitários para o recurso Vendedor */
public class VendedorResourceTest {

    /* Mock do VendedorBusiness */
    @Mock
    private VendedorBusiness vendedorBusiness;

    /* Mock do VendedorConverter */
    @Mock
    private VendedorConverter vendedorConverter;

    /* Classe sob teste */
    @InjectMocks
    private VendedorResource vendedorResource;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    /* Teste de inserção */
    @Test
    public void testInsert() {
        final VendedorDTO dto = new VendedorDTO();
        final Vendedor entity = new Vendedor();
        
        when(vendedorConverter.toEntity(dto)).thenReturn(entity);
        when(vendedorBusiness.insertOrUpdate(entity, TransactionType.INSERT)).thenReturn(entity);
        when(vendedorConverter.toDTO(entity)).thenReturn(dto);

        final ResponseEntity<?> response = vendedorResource.insert(dto);
        assertEquals(201, response.getStatusCode().value());
        assertEquals(dto, response.getBody());
    }

    @Test
    public void testUpdate() {
        final VendedorDTO dto = new VendedorDTO();
        final Vendedor entity = new Vendedor();
        
        when(vendedorConverter.toEntity(dto)).thenReturn(entity);
        when(vendedorBusiness.insertOrUpdate(entity, TransactionType.UPDATE)).thenReturn(entity);
        when(vendedorConverter.toDTO(entity)).thenReturn(dto);

        final ResponseEntity<?> response = vendedorResource.update(dto);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(dto, response.getBody());
    }

    /* Teste de deleção */
    @Test
    public void testDelete() {
        final UUID id = UUID.randomUUID();
        doNothing().when(vendedorBusiness).deleteById(id);
        
        final ResponseEntity<?> response = vendedorResource.delete(id);
        assertEquals(200, response.getStatusCode().value());
    }

    /* Teste de busca por ID */
    @Test
    public void testGetVendedorById() {
        final UUID id = UUID.randomUUID();
        final Vendedor entity = new Vendedor();
        final VendedorDTO dto = new VendedorDTO();
        
        when(vendedorBusiness.findById(id)).thenReturn(entity);
        when(vendedorConverter.toDTO(entity)).thenReturn(dto);
        
        final ResponseEntity<?> response = vendedorResource.getVendedorById(id);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(dto, response.getBody());
    }
    /* Teste de busca por CPF */
    @Test
    public void testGetVendedorByCpf() {
        final String cpf = "98765432100";
        final Vendedor entity = new Vendedor();
        final VendedorDTO dto = new VendedorDTO();

        when(vendedorBusiness.findByCpf(cpf)).thenReturn(entity);
        when(vendedorConverter.toDTO(entity)).thenReturn(dto);

        final ResponseEntity<?> response = vendedorResource.getVendedorByCpf(cpf);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(dto, response.getBody());
    }
}
