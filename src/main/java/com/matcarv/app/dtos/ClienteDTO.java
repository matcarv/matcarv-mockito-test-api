
package com.matcarv.app.dtos;


import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) para Cliente.
 * Utilizado para transferência de dados entre camadas.
 *
 * @author Weslley Matos
 */
@Data
@EqualsAndHashCode(callSuper = false, of = {"id"})
public class ClienteDTO {
    
    /** Identificador único do cliente */
    private UUID id;

    /** Nome completo do cliente */
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 255)
    private String nome;

    /** E-mail do cliente */
    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    /** Telefone de contato do cliente */
    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

    /** Endereço do cliente */
    @NotBlank(message = "Endereço é obrigatório")
    private String endereco;

    /** Número do endereço */
    @NotBlank(message = "Número é obrigatório")
    private String numero;

    /** Complemento do endereço */
    private String complemento;

    /** Bairro do cliente */
    @NotBlank(message = "Bairro é obrigatório")
    private String bairro;

    /** Cidade do cliente */
    @NotBlank(message = "Cidade é obrigatória")
    private String cidade;

    /** Estado do cliente */
    @NotBlank(message = "Estado é obrigatório")
    private String estado;

    /** CEP do cliente */
    @NotBlank(message = "CEP é obrigatório")
    private String cep;

    /** CPF do cliente */
    @NotBlank(message = "CPF é obrigatório")
    @Size(min = 11, max = 14, message = "CPF deve ter entre 11 e 14 caracteres")
    private String cpf;

    /** Observações adicionais sobre o cliente */
    private String observacao;

    /** Data de inclusão do cliente */
    private ZonedDateTime dataInclusao;
}
