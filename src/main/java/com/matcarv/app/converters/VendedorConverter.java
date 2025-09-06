package com.matcarv.app.converters;

import com.matcarv.app.entities.Endereco;
import com.matcarv.app.entities.Vendedor;
import com.matcarv.app.dtos.VendedorDTO;

import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.stereotype.Component;

/**
 * Conversor de Vendedor.
 * Responsável por converter entre Vendedor e VendedorDTO.
 *
 * @author Weslley Matos
 */
@Component
public class VendedorConverter {

    /** Converte um Vendedor para VendedorDTO
     *
     * @param vendedor o Vendedor a ser convertido
     * @return o VendedorDTO convertido
     */
    public VendedorDTO toDTO(final Vendedor vendedor) {
        final VendedorDTO dto = new VendedorDTO();
        dto.setId(vendedor.getId());
        dto.setNome(vendedor.getNome());
        dto.setEmail(vendedor.getEmail());
        dto.setTelefone(vendedor.getTelefone());

        if (vendedor.getEndereco() != null) {
            dto.setEndereco(vendedor.getEndereco().getLogradouro());
            dto.setNumero(vendedor.getEndereco().getNumero());
            dto.setComplemento(vendedor.getEndereco().getComplemento());
            dto.setBairro(vendedor.getEndereco().getBairro());
            dto.setCidade(vendedor.getEndereco().getCidade());
            dto.setEstado(vendedor.getEndereco().getEstado());
            dto.setCep(vendedor.getEndereco().getCep());
            
        }

        dto.setCpf(vendedor.getCpf());
        dto.setComissao(vendedor.getComissao());
        dto.setDataInclusao(vendedor.getDataInclusao().atStartOfDay(ZoneId.systemDefault()));

        return dto;
    }

    /** Converte um VendedorDTO para Vendedor
     *
     * @param dto o VendedorDTO a ser convertido
     * @return o Vendedor convertido
     */
    public Vendedor toEntity(final VendedorDTO dto) {
        final Vendedor vendedor = new Vendedor();
        vendedor.setId(dto.getId());
        vendedor.setNome(dto.getNome());
        vendedor.setEmail(dto.getEmail());
        vendedor.setTelefone(dto.getTelefone());

        final Endereco endereco = new Endereco();
        endereco.setLogradouro(dto.getEndereco());
        endereco.setNumero(dto.getNumero());
        endereco.setComplemento(dto.getComplemento());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setCep(dto.getCep());

        vendedor.setEndereco(endereco);

        vendedor.setCpf(dto.getCpf());
        vendedor.setComissao(dto.getComissao());
        vendedor.setDataInclusao(LocalDate.now());

        return vendedor;
    }
}
