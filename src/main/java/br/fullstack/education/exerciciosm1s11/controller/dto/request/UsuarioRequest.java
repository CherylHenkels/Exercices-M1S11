package br.fullstack.education.exerciciosm1s11.controller.dto.request;


public record UsuarioRequest(
        String usuario,
        String senha,
        String nomePapel
) {
}
