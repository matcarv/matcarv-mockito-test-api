package com.matcarv.app.resources;

import com.matcarv.app.business.ClienteBusiness;
import com.matcarv.app.enums.TransactionType;
import com.matcarv.app.converters.ClienteConverter;
import com.matcarv.app.dtos.ClienteDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.UUID;

/**
 * Controlador REST para operações de Cliente.
 * Expõe endpoints para CRUD e busca por CPF.
 *
 * @author Weslley Matos
 */
@RestController
@Tag(name = "Cliente", description = "Operações relacionadas a clientes")
public class ClienteResource {

    /* Injeção de dependência */
    private final ClienteBusiness clienteBusiness;

    /* Conversor de Cliente */
    private final ClienteConverter clienteConverter;

    /**
     * Injeção de dependências via construtor.
     * 
     * @param clienteBusiness o serviço de negócio de Cliente
     * @param clienteConverter o conversor de Cliente
     */
    public ClienteResource(
            final ClienteBusiness clienteBusiness, 
            final ClienteConverter clienteConverter) {
        this.clienteBusiness = clienteBusiness;
        this.clienteConverter = clienteConverter;
    }

    /** Endpoint para criação de Cliente
     *
     * @param clienteDTO Dados do Cliente a ser criado
     * @return ResponseEntity com o Cliente criado
     */
    @Operation(
        summary = "Criar cliente",
        description = """
        ### Códigos de resposta possíveis

        | Código | Significado                      |
        |--------|----------------------------------|
        | 201    | Cliente criado com sucesso       |
        | 400    | Dados inválidos                  |
        | 401    | Não autorizado                   |
        | 403    | Proibido                        |
        | 500    | Erro interno do servidor         |
        """
    )
    @PostMapping("/cliente")
    public ResponseEntity<?> insert(@Valid @RequestBody final ClienteDTO clienteDTO) {
        return ResponseEntity.created(null).body(
            clienteConverter.toDTO(
                clienteBusiness.insertOrUpdate(clienteConverter.toEntity(clienteDTO), TransactionType.INSERT)
            )
        );
    }

    /** Endpoint para atualização de Cliente
     *
     * @param clienteDTO Dados do Cliente a ser atualizado
     * @return ResponseEntity com o Cliente atualizado
     */
    @Operation(
        summary = "Atualizar cliente",
        description = """
        ### Códigos de resposta possíveis

        | Código | Significado                      |
        |--------|----------------------------------|
        | 200    | Cliente atualizado com sucesso   |
        | 400    | Dados inválidos                  |
        | 401    | Não autorizado                   |
        | 403    | Proibido                        |
        | 404    | Cliente não encontrado           |
        | 500    | Erro interno do servidor         |
        """
    )
    @PutMapping("/cliente")
    public ResponseEntity<?> update(@Valid @RequestBody final ClienteDTO clienteDTO) {
        return ResponseEntity.ok(
            clienteConverter.toDTO(
                clienteBusiness.insertOrUpdate(clienteConverter.toEntity(clienteDTO), TransactionType.UPDATE)
            )
        );
    }

    /** Endpoint para exclusão de Cliente
     *
     * @param id ID do Cliente a ser excluído
     * @return ResponseEntity com status da operação
     */
    @Operation(
        summary = "Excluir cliente",
        description = """
        ### Códigos de resposta possíveis

        | Código | Significado                      |
        |--------|----------------------------------|
        | 200    | Cliente excluído com sucesso     |
        | 400    | Requisição inválida              |
        | 401    | Não autorizado                   |
        | 403    | Proibido                        |
        | 404    | Cliente não encontrado           |
        | 500    | Erro interno do servidor         |
        """
    )
    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable final UUID id) {
        clienteBusiness.deleteById(id);

        return ResponseEntity.ok().build();
    }

    /** Obter Cliente por ID
     *
     * @param id ID do Cliente
     * @return ClienteDTO
     */
    @Operation(
        summary = "Buscar cliente por ID",
        description = """
        ### Códigos de resposta possíveis

        | Código | Significado                      |
        |--------|----------------------------------|
        | 200    | Cliente encontrado               |
        | 400    | Requisição inválida              |
        | 401    | Não autorizado                   |
        | 403    | Proibido                        |
        | 404    | Cliente não encontrado           |
        | 500    | Erro interno do servidor         |
        """
    )
    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> getClienteById(@PathVariable final UUID id) {
        return ResponseEntity.ok(
            clienteConverter.toDTO(
                clienteBusiness.findById(id)
            )
        );
    }

    /** Obter Cliente por ID
     *
     * @param id ID do Cliente
     * @return ClienteDTO
     */
    @Operation(
        summary = "Buscar cliente por CPF",
        description = """
        ### Códigos de resposta possíveis

        | Código | Significado                      |
        |--------|----------------------------------|
        | 200    | Cliente encontrado               |
        | 400    | Requisição inválida              |
        | 401    | Não autorizado                   |
        | 403    | Proibido                        |
        | 404    | Cliente não encontrado           |
        | 500    | Erro interno do servidor         |
        """
    )
    @GetMapping("/cliente/cpf/{cpf}")
    public ResponseEntity<?> getClienteByCpf(@PathVariable final String cpf) {
        return ResponseEntity.ok(
            clienteConverter.toDTO(
                clienteBusiness.findByCpf(cpf)
            )
        );
    }
}
