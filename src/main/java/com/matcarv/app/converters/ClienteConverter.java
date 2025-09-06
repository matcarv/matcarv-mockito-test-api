package com.matcarv.app.converters;

import com.matcarv.app.entities.Cliente;
import com.matcarv.app.entities.Endereco;
import com.matcarv.app.dtos.ClienteDTO;

import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.stereotype.Component;

/* Conversor de Cliente */
@Component
public class ClienteConverter {

    /* Converte um Cliente para ClienteDTO
     *
     * @param cliente o Cliente a ser convertido
     * @return o ClienteDTO convertido
    */
    public ClienteDTO toDTO(final Cliente cliente) {
        final ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setEmail(cliente.getEmail());
        dto.setTelefone(cliente.getTelefone());

        if (cliente.getEndereco() != null) {
            dto.setEndereco(cliente.getEndereco().getLogradouro());
            dto.setNumero(cliente.getEndereco().getNumero());
            dto.setComplemento(cliente.getEndereco().getComplemento());
            dto.setBairro(cliente.getEndereco().getBairro());
            dto.setCidade(cliente.getEndereco().getCidade());
            dto.setEstado(cliente.getEndereco().getEstado());
            dto.setCep(cliente.getEndereco().getCep());
        }

        dto.setCpf(cliente.getCpf());
        dto.setObservacao(cliente.getObservacao());
        dto.setDataInclusao(cliente.getDataInclusao().atStartOfDay(ZoneId.systemDefault()));

        return dto;
    }

    /* Converte um ClienteDTO para Cliente
     *
     * @param dto o ClienteDTO a ser convertido
     * @return o Cliente convertido
    */
    public Cliente toEntity(final ClienteDTO dto) {
        final Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());

        final Endereco endereco = new Endereco();
        endereco.setLogradouro(dto.getEndereco());
        endereco.setNumero(dto.getNumero());
        endereco.setComplemento(dto.getComplemento());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setCep(dto.getCep());

        cliente.setEndereco(endereco);

        cliente.setCpf(dto.getCpf());
        cliente.setObservacao(dto.getObservacao());
        cliente.setDataInclusao(LocalDate.now());

        return cliente;
    }
}
