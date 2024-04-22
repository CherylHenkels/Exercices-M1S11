package br.fullstack.education.exerciciosm1s11.controller.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CadernoRequest{
    private String nome;
    private Long id_usuario;
}
