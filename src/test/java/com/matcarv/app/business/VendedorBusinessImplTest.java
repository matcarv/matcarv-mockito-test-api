package com.matcarv.app.business;

import com.matcarv.app.entities.Vendedor;
import com.matcarv.app.enums.TransactionType;
import com.matcarv.app.repository.VendedorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/* Test class for VendedorBusinessImpl */
public class VendedorBusinessImplTest {

    /* Repository for Vendedor entity */
    @Mock
    private VendedorRepository vendedorRepository;

    /* Business implementation for Vendedor entity */
    @InjectMocks
    private VendedorBusinessImpl vendedorBusiness;

    /* Initialization */
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    /* Test findById method */
    @Test
    public void testFindById() {
        final Vendedor entity = createVendedor();

        when(vendedorRepository.findById(entity.getId())).thenReturn(Optional.of(entity));

        final Vendedor result = vendedorBusiness.findById(entity.getId());
        
        assertNotNull(result);
        assertEquals(entity.getId(), result.getId());
    }

    /* Test deleteById method */
    @Test
    public void testDeleteById() {
        final UUID id = UUID.randomUUID();
       
        doNothing().when(vendedorRepository).deleteById(id);
        assertDoesNotThrow(() -> vendedorBusiness.deleteById(id));
        verify(vendedorRepository, times(1)).deleteById(id);
    }

    /* Test insertOrUpdate method for INSERT operation */
    @Test
    public void testInsertOrUpdateInsert() {
        final Vendedor entity = createVendedor();
        
        when(vendedorRepository.save(entity)).thenReturn(entity);
        
        final Vendedor result = vendedorBusiness.insertOrUpdate(entity, TransactionType.INSERT);
        assertNotNull(result);
        verify(vendedorRepository, times(1)).save(entity);
    }

    /* Test insertOrUpdate method for UPDATE operation */
    @Test
    public void testInsertOrUpdateUpdate() {
        final Vendedor entity = createVendedor();

        when(vendedorRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(vendedorRepository.save(entity)).thenReturn(entity);

        final Vendedor result = vendedorBusiness.insertOrUpdate(entity, TransactionType.UPDATE);

        assertNotNull(result);
        assertEquals(entity.getId(), result.getId());
        verify(vendedorRepository, times(1)).findById(entity.getId());
        verify(vendedorRepository, times(1)).save(entity);
    }

    @Test
    public void testFindByCpf() {
        final Vendedor entity = createVendedor();
        final String cpf = "98765432100";

        when(vendedorRepository.findByCpf(cpf)).thenReturn(Optional.of(entity));

        final Vendedor result = vendedorBusiness.findByCpf(cpf);

        assertNotNull(result);
        assertEquals(cpf, result.getCpf());
        verify(vendedorRepository, times(1)).findByCpf(cpf);
    }

    /**
     * Helper method to create a Vendedor entity
     * @return
     */
    private Vendedor createVendedor() {
        Vendedor vendedor = new Vendedor();
        vendedor.setId(UUID.randomUUID());
        vendedor.setCpf("98765432100");
        return vendedor;
    }
}
