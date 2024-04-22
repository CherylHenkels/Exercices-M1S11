package br.fullstack.education.exerciciosm1s11.service;

import br.fullstack.education.exerciciosm1s11.controller.dto.request.CadernoRequest;
import br.fullstack.education.exerciciosm1s11.controller.dto.response.CadernoResponse;

import java.util.List;

public interface CadernoService {

    List<CadernoResponse> buscarTodos();

    CadernoResponse buscarPorId(Long id);

    CadernoResponse criar(CadernoRequest cadernoRequest);

    CadernoResponse alterar(Long id, CadernoRequest cadernoRequest);

    void excluir(Long id);

}
