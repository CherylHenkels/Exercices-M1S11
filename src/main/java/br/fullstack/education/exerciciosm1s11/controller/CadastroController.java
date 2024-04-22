package br.fullstack.education.exerciciosm1s11.controller;

import br.fullstack.education.exerciciosm1s11.controller.dto.request.UsuarioRequest;
import br.fullstack.education.exerciciosm1s11.controller.dto.response.UsuarioResponse;
import br.fullstack.education.exerciciosm1s11.infra.utils.JsonUtil;
import br.fullstack.education.exerciciosm1s11.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j

public class CadastroController {

    private final UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioResponse> novoUsuario(
            @RequestHeader(name = "Authorization") String token,
             @RequestBody UsuarioRequest usuarioRequest
    ) {
        log.info("POST /cadastro -> Início");
        UsuarioResponse usuario = usuarioService.cadastraNovoUsuario(usuarioRequest, token.substring(7));
        log.info("POST /cadastro -> Usuário cadastrado com sucesso");
        log.info("POST /cadastro -> 201 CREATED");
        log.debug("POST /cadastro -> Response Body:\n{}\n", JsonUtil.objetoParaJson(usuario));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

}