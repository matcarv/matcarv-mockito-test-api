package com.matcarv.app.business;

import com.matcarv.app.dtos.VendedorSearchDTO;
import com.matcarv.app.entities.Vendedor;
import com.matcarv.app.enums.TransactionType;
import com.matcarv.app.repository.VendedorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.*;

/* Test class for VendedorBusinessImpl */
class VendedorBusinessTest {

    /* Repository for Vendedor entity */
    @Mock
    private VendedorRepository vendedorRepository;

    /* Business implementation for Vendedor entity */
    @InjectMocks
    private VendedorBusinessImpl vendedorBusiness;

    /* Initialization */
    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    /* Test findById method */
    @Test
    void testFindById() {
        final Vendedor entity = createVendedor();

        when(vendedorRepository.findById(entity.getId())).thenReturn(Optional.of(entity));

        final Vendedor result = vendedorBusiness.findById(entity.getId());
        
        assertNotNull(result);
        assertEquals(entity.getId(), result.getId());
    }

    /* Test deleteById method */
    @Test
    void testDeleteById() {
        final UUID id = UUID.randomUUID();
       
        doNothing().when(vendedorRepository).deleteById(id);
        assertDoesNotThrow(() -> vendedorBusiness.deleteById(id));
        verify(vendedorRepository, times(1)).deleteById(id);
    }

    /* Test insertOrUpdate method for INSERT operation */
    @Test
    void testInsertOrUpdateInsert() {
        final Vendedor entity = createVendedor();
        
        when(vendedorRepository.save(entity)).thenReturn(entity);
        
        final Vendedor result = vendedorBusiness.insertOrUpdate(entity, TransactionType.INSERT);
        assertNotNull(result);
        verify(vendedorRepository, times(1)).save(entity);
    }

    /* Test insertOrUpdate method for UPDATE operation */
    @Test
    void testInsertOrUpdateUpdate() {
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
    void testFindByCpf() {
        final Vendedor entity = createVendedor();
        final String cpf = "98765432100";

        when(vendedorRepository.findByCpf(cpf)).thenReturn(Optional.of(entity));

        final Vendedor result = vendedorBusiness.findByCpf(cpf);

        assertNotNull(result);
        assertEquals(cpf, result.getCpf());
        verify(vendedorRepository, times(1)).findByCpf(cpf);
    }

    /* Test findByFilter method */
    @Test
    void testFindByFilter() {
        final Vendedor vendedor1 = createVendedor();
        vendedor1.setNome("João");
        vendedor1.setEmail("joao@email.com");

        when(vendedorRepository.findAll(ArgumentMatchers.<Specification<Vendedor>> any()))
            .thenReturn(List.of(vendedor1));

        final List<VendedorSearchDTO> result = vendedorBusiness.findByFilter(Map.of("nome", "João", "cpf", "98765432100"));

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("João", result.get(0).getNome());
        assertEquals("joao@email.com", result.get(0).getEmail());
        verify(vendedorRepository, times(1)).findAll(any(Specification.class));
    }

     /* Test findByFilter method */
    @Test
    void testFindByFilterWithoutFilter() {
        final Vendedor vendedor1 = createVendedor();
        vendedor1.setNome("João");
        vendedor1.setEmail("joao@email.com");

        final Vendedor vendedor2 = createVendedor();
        vendedor2.setNome("Maria");
        vendedor2.setEmail("maria@email.com");

        when(vendedorRepository.findAll(ArgumentMatchers.<Specification<Vendedor>> any()))
            .thenReturn(List.of(vendedor1, vendedor2));

        // Call with an empty filter map to test behavior without filters
        final List<VendedorSearchDTO> result = vendedorBusiness.findByFilter(anyMap());

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("João", result.get(0).getNome());
        assertEquals("Maria", result.get(1).getNome());
        assertEquals("joao@email.com", result.get(0).getEmail());
        assertEquals("maria@email.com", result.get(1).getEmail());
        verify(vendedorRepository, times(1)).findAll(any(Specification.class));
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
