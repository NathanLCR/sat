package com.nathan.sat.service.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContatoDTO implements Serializable {

    private Integer id;

    @NotBlank(message = "Nome é obrigatorio")
    @Size(max = 100, message = "Nome muito longo")
    private String nome;

    @NotBlank(message = "Email é obrigatorio")
    @Email(message = "Email invalido")
    @Size(max = 255, message = "Email muito longo")
    private String email;

    @NotBlank(message = "Número de celular é obrigatorio")
    @Size(max = 11, min = 11, message = "Número de celular deve ter 11 caracteres")
    private String celular;

    private String telefone;

    @Pattern(regexp = "[SN]", message = "Valor deve ser S ou N")
    private String favorito;

    @Pattern(regexp = "[SN]", message = "Valor deve ser S ou N")
    private String ativo;

    private LocalDateTime dataHoraCadastro;
}
