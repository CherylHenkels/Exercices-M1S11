package br.fullstack.education.exerciciosm1s11.service;

import br.fullstack.education.exerciciosm1s11.controller.dto.response.LoginResponse;
import br.fullstack.education.exerciciosm1s11.controller.dto.request.LoginRequest;


public interface TokenService {

    LoginResponse gerarToken(LoginRequest loginRequest);

    String buscaCampo(String token, String sub);
}
