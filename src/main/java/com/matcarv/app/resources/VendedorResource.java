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

    /* Injeção de dependência */
    private final VendedorBusiness vendedorBusiness;

    /* Conversor de Vendedor */
    private final VendedorConverter vendedorConverter;

    /**
     * Injeção de dependências via construtor.
     * 
     * @param vendedorBusiness o serviço de negócio de Vendedor
     * @param vendedorConverter o conversor de Vendedor
     */
    public VendedorResource(
            final VendedorBusiness vendedorBusiness, 
            final VendedorConverter vendedorConverter) {
        this.vendedorBusiness = vendedorBusiness;
        this.vendedorConverter = vendedorConverter;
    }

    /**
     * Endpoint para criação de Vendedor
     * 
     * @param vendedorDTO Dados do Vendedor a ser criado
     * @return ResponseEntity com o Vendedor criado
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
     *
     * @param vendedorDTO Dados do Vendedor a ser atualizado
     * @return ResponseEntity com o Vendedor atualizado
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
     * 
     * @param id ID do Vendedor a ser excluído
     * @return ResponseEntity sem conteúdo
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
     * 
     * @param id ID do Vendedor a ser obtido
     * @return ResponseEntity com o Vendedor encontrado
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
     * 
     * @param cpf CPF do Vendedor a ser obtido
     * @return ResponseEntity com o Vendedor encontrado
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
