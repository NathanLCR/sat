package com.nathan.sat.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "contato", schema = "desafio")
@Entity
@Data
public class Contato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contato_id")
    private Integer id;

    @Column(name = "contato_nome")
    private String nome;

    @Column(name = "contato_email")
    private String email;

    @Column(name = "contato_celular")
    private String celular;

    @Column(name = "contato_telefone")
    private String telefone;

    @Column(name = "contato_sn_favorito")
    private String favorito;

    @Column(name = "contato_sn_ativo")
    private String ativo;

    @CreatedDate
    @Column(name = "contato_dh_cad")
    private LocalDateTime dataHoraCadastro;
}
