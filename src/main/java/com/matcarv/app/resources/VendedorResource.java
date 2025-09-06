package com.matcarv.app.resources;

import com.matcarv.app.business.VendedorBusiness;
import com.matcarv.app.enums.TransactionType;
import com.matcarv.app.converters.VendedorConverter;
import com.matcarv.app.dtos.VendedorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.UUID;

/**
 * Controlador de Vendedores
 */
@RestController
@Tag(name = "Vendedor", description = "Operações relacionadas a vendedores")
public class VendedorResource {

    @Autowired
    private VendedorBusiness vendedorBusiness;

    @Autowired
    private VendedorConverter vendedorConverter;

    /**
     * Endpoint para criação de Vendedor
     */
    @Operation(
        summary = "Criar vendedor",
        description = """
        ### Códigos de resposta possíveis

        | Código | Significado                      |
        |--------|----------------------------------|
        | 201    | Vendedor criado com sucesso      |
        | 400    | Dados inválidos                  |
        | 401    | Não autorizado                   |
        | 403    | Proibido                        |
        | 500    | Erro interno do servidor         |
        """
    )
    @PostMapping("/vendedor")
    public ResponseEntity<?> insert(@Valid @RequestBody final VendedorDTO vendedorDTO) {
        return ResponseEntity.created(null).body(
            vendedorConverter.toDTO(
                vendedorBusiness.insertOrUpdate(vendedorConverter.toEntity(vendedorDTO), TransactionType.INSERT)
            )
        );
    }

    /**
     * Endpoint para atualização de Vendedor
     */
    @Operation(
        summary = "Atualizar vendedor",
        description = """
        ### Códigos de resposta possíveis

        | Código | Significado                      |
        |--------|----------------------------------|
        | 200    | Vendedor atualizado com sucesso  |
        | 400    | Dados inválidos                  |
        | 401    | Não autorizado                   |
        | 403    | Proibido                        |
        | 404    | Vendedor não encontrado          |
        | 500    | Erro interno do servidor         |
        """
    )
    @PutMapping("/vendedor")
    public ResponseEntity<?> update(@Valid @RequestBody final VendedorDTO vendedorDTO) {
        return ResponseEntity.ok(
            vendedorConverter.toDTO(
                vendedorBusiness.insertOrUpdate(vendedorConverter.toEntity(vendedorDTO), TransactionType.UPDATE)
            )
        );
    }

    /**
     * Endpoint para exclusão de Vendedor
     */
    @Operation(
        summary = "Excluir vendedor",
        description = """
        ### Códigos de resposta possíveis

        | Código | Significado                      |
        |--------|----------------------------------|
        | 200    | Vendedor excluído com sucesso    |
        | 400    | Requisição inválida              |
        | 401    | Não autorizado                   |
        | 403    | Proibido                        |
        | 404    | Vendedor não encontrado          |
        | 500    | Erro interno do servidor         |
        """
    )
    @DeleteMapping("/vendedor/{id}")
    public ResponseEntity<?> delete(@PathVariable final UUID id) {
        vendedorBusiness.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Obter Vendedor por ID
     */
    @Operation(
        summary = "Buscar vendedor por ID",
        description = """
        ### Códigos de resposta possíveis

        | Código | Significado                      |
        |--------|----------------------------------|
        | 200    | Vendedor encontrado              |
        | 400    | Requisição inválida              |
        | 401    | Não autorizado                   |
        | 403    | Proibido                        |
        | 404    | Vendedor não encontrado          |
        | 500    | Erro interno do servidor         |
        """
    )
    @GetMapping("/vendedor/{id}")
    public ResponseEntity<?> getVendedorById(@PathVariable final UUID id) {
        return ResponseEntity.ok(
            vendedorConverter.toDTO(
                vendedorBusiness.findById(id)
            )
        );
    }

    /**
     * Obter Vendedor por CPF
     */
    @Operation(
        summary = "Buscar vendedor por CPF",
        description = """
        ### Códigos de resposta possíveis

        | Código | Significado                      |
        |--------|----------------------------------|
        | 200    | Vendedor encontrado              |
        | 400    | Requisição inválida              |
        | 401    | Não autorizado                   |
        | 403    | Proibido                        |
        | 404    | Vendedor não encontrado          |
        | 500    | Erro interno do servidor         |
        """
    )
    @GetMapping("/vendedor/cpf/{cpf}")
    public ResponseEntity<?> getVendedorByCpf(@PathVariable final String cpf) {
        return ResponseEntity.ok(
            vendedorConverter.toDTO(
                vendedorBusiness.findByCpf(cpf)
            )
        );
    }
}
