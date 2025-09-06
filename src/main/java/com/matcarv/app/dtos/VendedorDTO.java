package com.matcarv.app.dtos;


import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) para Vendedor.
 * Utilizado para transferência de dados entre camadas.
 *
 * @author Weslley Matos
 */
@Data
@EqualsAndHashCode(callSuper = false, of = {"id"})
public class VendedorDTO {

	/** Identificador único do vendedor */
	private UUID id;

	/** Nome completo do vendedor */
	@NotBlank(message = "Nome é obrigatório")
	@Size(max = 255)
	private String nome;

	/** E-mail do vendedor */
	@NotBlank(message = "E-mail é obrigatório")
	@Email(message = "E-mail inválido")
	private String email;

	/** Telefone de contato do vendedor */
	@NotBlank(message = "Telefone é obrigatório")
	private String telefone;

	/** Endereço do vendedor */
	@NotBlank(message = "Endereço é obrigatório")
	private String endereco;

	/** Número do endereço */
	@NotBlank(message = "Número é obrigatório")
	private String numero;

	/** Complemento do endereço */
	@NotBlank(message = "Complemento é obrigatório")
	private String complemento;

	/** Bairro do vendedor */
	@NotBlank(message = "Bairro é obrigatório")
	private String bairro;

	/** Cidade do vendedor */
	@NotBlank(message = "Cidade é obrigatória")
	private String cidade;

	/** Estado do vendedor */
	@NotBlank(message = "Estado é obrigatório")
	private String estado;

	/** CEP do vendedor */
	@NotBlank(message = "CEP é obrigatório")
	private String cep;

	/** CPF do vendedor */
	@NotBlank(message = "CPF é obrigatório")
	@Size(min = 11, max = 14, message = "CPF deve ter entre 11 e 14 caracteres")
	private String cpf;

	/** Comissão do vendedor */
	@NotNull(message = "Comissão é obrigatória")
	private BigDecimal comissao;

	/** Data de inclusão do vendedor */
	private ZonedDateTime dataInclusao;
}
