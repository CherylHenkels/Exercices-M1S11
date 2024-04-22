package br.fullstack.education.exerciciosm1s11.controller;

import br.fullstack.education.exerciciosm1s11.controller.dto.request.CadernoRequest;
import br.fullstack.education.exerciciosm1s11.controller.dto.response.CadernoResponse;
import br.fullstack.education.exerciciosm1s11.service.CadernoService;
import br.fullstack.education.exerciciosm1s11.infra.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("cadernos")

public class CadernoController {

    private final CadernoService service;

    @GetMapping
    public ResponseEntity<List<CadernoResponse>> buscarTodos() {
        log.info("GET /cadernos -> Início");
        List<CadernoResponse> cadernos = service.buscarTodos();
        log.info("GET /cadernos -> Encontrados {} registros", cadernos.size());
        log.info("GET /cadernos -> 200 OK");
        log.debug("GET /cadernos -> Response Body:\n{}\n", JsonUtil.objetoParaJson(cadernos));
        return ResponseEntity.status(HttpStatus.OK).body(cadernos);
    }


    @GetMapping("{id}")
    public ResponseEntity<CadernoResponse> buscarPorId(@PathVariable Long id) {
        log.info("GET /cadernos/{} -> Início" , id );
        CadernoResponse caderno = service.buscarPorId(id);
        log.info("GET /cadernos/{} -> Caderno encontrado", id);
        log.info("GET /cadernos/{} -> 200 OK", id);
        log.debug("GET /cadernos/{} -> Response Body:\n{}\n", id, JsonUtil.objetoParaJson(caderno));
        return ResponseEntity.status(HttpStatus.OK).body(caderno);
    }

//    @GetMapping({"{id_caderno}/notas"})
//    public ResponseEntity<List<NotaResponse>> buscarNotasPorCadernoId(@PathVariable("id_caderno") Long idCaderno) {
//        log.info("GET /cadernos/{id_caderno}/notas -> Início");
//        List<NotaResponse> notas = notaService.buscarPorCadernoId(idCaderno);
//        log.info("GET /cadernos/{id_caderno}/notas -> Encontrados {} registros", notas.size());
//        log.info("GET /cadernos/{id_caderno}/notas-> 200 OK");
//        log.debug("GET /cadernos/{id_caderno}/notas -> Response Body:\n{}\n", JsonUtil.objetoParaJson(notas));
//        return ResponseEntity.status(HttpStatus.OK).body(notas);
//    }


    @PostMapping
    public ResponseEntity<CadernoResponse> criarCaderno(@RequestBody CadernoRequest cadernoRequest) {
        log.info("POST /cadernos -> Início");
        CadernoResponse caderno = service.criar(cadernoRequest);
        log.info("POST /cadernos -> Caderno criado com sucesso.");
        log.info("POST /cadernos -> 201 CREATED");
        log.debug("POST /cadernos -> Response Body:\n{}\n", JsonUtil.objetoParaJson(caderno));
        return ResponseEntity.status(HttpStatus.CREATED).body(caderno);
    }


    @PutMapping("{id}")
    public ResponseEntity<CadernoResponse> alterarCaderno(@PathVariable Long id, @RequestBody CadernoRequest cadernoRequest) {
        log.info("PUT /cadernos/{} -> Início", id);
        CadernoResponse caderno = service.alterar(id, cadernoRequest);
        log.info("PUT /cadernos/{} -> Caderno atualizado com sucesso", id);
        log.info("PUT /cadernos/{} -> 200 OK", id);
        log.debug("PUT /cadernos/{} -> Response Body:\n{}\n", id, JsonUtil.objetoParaJson(caderno));
        return ResponseEntity.status(HttpStatus.OK).body(caderno);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarCaderno(@PathVariable Long id) {
        log.info("DELETE /cadernos/{} -> Início", id);
        service.excluir(id);
        log.info("DELETE /cadernos/{} -> Caderno excluído com sucesso", id);
        log.info("DELETE /cadernos/{} -> 204 NO CONTENT", id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("{id}/pontuacao")
//    public ResponseEntity<Double> pontuacaoCaderno(@PathVariable Long id) {
//        log.info("GET /cadernos/{id}/pontuacao -> Início");
//        Double pontuacao = notaService.calcularPontuacaoCaderno(id);
//        log.info("GET /cadernos/{}/pontuacao -> Pontuação calculada com sucesso.", id);
//        log.info("GET /cadernos/{}/pontuacao -> 200 OK", id);
//        log.debug("GET /cadernos/{}/pontuacao -> Response Body:\n{}\n", id, JsonUtil.objetoParaJson(pontuacao));
//        return ResponseEntity.status(HttpStatus.OK).body(pontuacao);
//    }
}
