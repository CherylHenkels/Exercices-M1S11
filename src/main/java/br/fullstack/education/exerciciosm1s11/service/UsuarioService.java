package br.fullstack.education.exerciciosm1s11.service;

import br.fullstack.education.exerciciosm1s11.controller.dto.request.UsuarioRequest;
import br.fullstack.education.exerciciosm1s11.controller.dto.response.UsuarioResponse;



public interface UsuarioService {

    UsuarioResponse cadastraNovoUsuario(UsuarioRequest usuarioRequest, String token);

}
