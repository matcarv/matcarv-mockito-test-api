package com.matcarv.app.converters;

import com.matcarv.app.dtos.ClienteDTO;
import com.matcarv.app.entities.Cliente;
import com.matcarv.app.entities.Endereco;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

/* Testes unitários para o ClienteConverter */
public class ClienteConverterTest {

    /* Instância do ClienteConverter */
    private final ClienteConverter converter = new ClienteConverter();

    /**
     * Teste do método toDTO e toEntity do ClienteConverter
     */
    @Test
    public void testToDTO() {
        final Cliente cliente = new Cliente();
        cliente.setNome("Teste");
        cliente.setCpf("12345678901");
        cliente.setDataInclusao(LocalDate.now());
        cliente.setEndereco(new Endereco());
        cliente.getEndereco().setLogradouro("Rua Teste, 456");
        cliente.getEndereco().setCidade("Cidade Teste");
        cliente.getEndereco().setEstado("TS");
        cliente.getEndereco().setCep("98765-432");

        final ClienteDTO dto = converter.toDTO(cliente);
        assertNotNull(dto);
        assertEquals("Teste", dto.getNome());
        assertEquals("12345678901", dto.getCpf());
    }

    /**
     * Teste do método toEntity do ClienteConverter
     */
    @Test
    public void testToEntity() {
        final ClienteDTO dto = new ClienteDTO();
        dto.setNome("Teste DTO");
        dto.setCpf("98765432100");
        dto.setEndereco("Rua Teste, 456");
        dto.setCidade("Cidade Teste");
        dto.setEstado("TS");
        dto.setCep("98765-432");

        final Cliente entity = converter.toEntity(dto);
        assertNotNull(entity);
        assertEquals("Teste DTO", entity.getNome());
        assertEquals("98765432100", entity.getCpf());

    }
}
