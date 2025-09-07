package com.matcarv.app.converters;

import com.matcarv.app.dtos.VendedorDTO;
import com.matcarv.app.entities.Endereco;
import com.matcarv.app.entities.Vendedor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

/* Testes unitários para o VendedorConverter */
class VendedorConverterTest {

    /* Instância do VendedorConverter */
    private final VendedorConverter converter = new VendedorConverter();
    
    /* Teste do método toDTO do VendedorConverter */
    @Test
    void testToDTO() {
        final Vendedor vendedor = new Vendedor();
        vendedor.setNome("Vendedor Teste");
        vendedor.setCpf("11122233344");
        vendedor.setDataInclusao(LocalDate.now());
        vendedor.setEndereco(new Endereco());
        vendedor.getEndereco().setLogradouro("Rua Exemplo, 123");
        vendedor.getEndereco().setCidade("Cidade Exemplo");
        vendedor.getEndereco().setEstado("EX");
        vendedor.getEndereco().setCep("12345-678");

        final VendedorDTO dto = converter.toDTO(vendedor);
        assertNotNull(dto);
        assertEquals("Vendedor Teste", dto.getNome());
        assertEquals("11122233344", dto.getCpf());
    }

    /* Teste do método toEntity do VendedorConverter */
    @Test
    void testToEntity() {
        final VendedorDTO dto = new VendedorDTO();
        dto.setNome("DTO Vendedor");
        dto.setCpf("55566677788");
        dto.setEndereco("Rua Exemplo, 123");
        dto.setCidade("Cidade Exemplo");
        dto.setEstado("EX");
        dto.setCep("12345-678");

        final Vendedor entity = converter.toEntity(dto);
        assertNotNull(entity);
        assertEquals("DTO Vendedor", entity.getNome());
        assertEquals("55566677788", entity.getCpf());
        assertEquals("Rua Exemplo, 123", entity.getEndereco().getLogradouro());
        assertEquals("Cidade Exemplo", entity.getEndereco().getCidade());
        assertEquals("EX", entity.getEndereco().getEstado());
        assertEquals("12345-678", entity.getEndereco().getCep());
    }
}
