package com.matcarv.app.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Repository interface for Cliente entity.
 */
@Data
@Entity
@Table(name = "CLIENTE")
@PrimaryKeyJoinColumn(name = "id")
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Pessoa {

    /** Observações adicionais sobre a pessoa */
    @Column(name = "OBSERVACAO", length = 4000)
    private String observacao;
}
