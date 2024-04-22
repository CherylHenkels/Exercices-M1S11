package br.fullstack.education.exerciciosm1s11.service;

import br.fullstack.education.exerciciosm1s11.controller.dto.response.CadernoResponse;
import br.fullstack.education.exerciciosm1s11.controller.dto.request.CadernoRequest;
import br.fullstack.education.exerciciosm1s11.datasource.entity.CadernoEntity;
import br.fullstack.education.exerciciosm1s11.datasource.entity.UsuarioEntity;
import br.fullstack.education.exerciciosm1s11.datasource.repository.CadernoRepository;
import br.fullstack.education.exerciciosm1s11.datasource.repository.UsuarioRepository;
import br.fullstack.education.exerciciosm1s11.infra.exception.InvalidRequestException;
import br.fullstack.education.exerciciosm1s11.infra.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service

public class CadernoServiceImpl implements CadernoService {

    private final CadernoRepository cadernoRepository;
    private final UsuarioRepository usuarioRepository;

    public CadernoServiceImpl(CadernoRepository cadernoRepository, UsuarioRepository usuarioRepository) {
        this.cadernoRepository = cadernoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<CadernoResponse> buscarTodos() {
        List<CadernoEntity> cadernos = cadernoRepository.findAll();

        if (cadernos.isEmpty()) {
            log.error("404 NOT FOUND -> Não há cadernos cadastrados.");
            throw new NotFoundException("Não há cadernos cadastrados.");
        }

        return cadernos.stream().map( //mapear a lista de CadernoEntity para uma lista de CadernoResponse
                t -> new CadernoResponse(t.getId(), t.getNome(), t.getUsuario().getId() )
        ).toList();
    }

    @Override
    public CadernoResponse buscarPorId(Long id) {

        CadernoEntity caderno = cadernoRepository.findById(id)
                .orElseThrow(()-> {
                    log.error("404 NOT FOUND -> Caderno não encontrado com id: {}" , id);
                    return new NotFoundException("Caderno não encontrado com id:" + id);});

        return new CadernoResponse(caderno.getId(), caderno.getNome(),caderno.getUsuario().getId());
    }

    @Override
    public CadernoResponse criar(CadernoRequest cadernoRequest) {

        if (cadernoRequest.getNome() == null || cadernoRequest.getNome().trim().isEmpty()) {
            log.error("400 BAD REQUEST -> Nome do caderno é obrigatório");
            throw new InvalidRequestException("Nome do caderno é obrigatório");
        }
        if (cadernoRequest.getId_usuario() == null ) {
            log.error("400 BAD REQUEST -> Id do usuario é obrigatório");
            throw new InvalidRequestException("Id do usuario é obrigatório");
        }


        // cria usuario que será vinculado ao caderno
        UsuarioEntity cadernoUsuario = usuarioRepository.findById(cadernoRequest.getId_usuario())
                .orElseThrow(() -> {
                    log.error("404 NOT FOUND ->Usuário não encontrado com id: {}" , cadernoRequest.getId_usuario());
                    return new NotFoundException("Usuário não encontrado com id:" + cadernoRequest.getId_usuario());});


        CadernoEntity caderno = new CadernoEntity();
        caderno.setId(null); // Garante que um novo ID será gerado.
        caderno.setNome(cadernoRequest.getNome());
        caderno.setUsuario(cadernoUsuario); // Faz o link do usuario com o caderno
        cadernoRepository.save(caderno);

        return new CadernoResponse(caderno.getId(), caderno.getNome(),
                caderno.getUsuario().getId());
    }

    @Override
    public CadernoResponse alterar(Long id, CadernoRequest cadernoRequest) {
        buscarPorId(id); // Verifica a existência do Caderno.

        if (cadernoRequest.getNome() == null || cadernoRequest.getNome().trim().isEmpty()) {
            log.error("400 BAD REQUEST -> Nome do caderno é obrigatório");
            throw new InvalidRequestException("Nome do caderno é obrigatório");
        }
        if (cadernoRequest.getId_usuario() == null ) {
            log.error("400 BAD REQUEST -> Id do usuario é obrigatório");
            throw new InvalidRequestException("Id do usuario é obrigatório");
        }


        // cria usuario que será vinculado ao caderno
        UsuarioEntity cadernoUsuario = usuarioRepository.findById(cadernoRequest.getId_usuario())
                .orElseThrow(() -> {
                    log.error("404 NOT FOUND ->Usuário não encontrado com id: {}" , cadernoRequest.getId_usuario());
                    return new NotFoundException("Usuário não encontrado com id:" + cadernoRequest.getId_usuario());});


        CadernoEntity caderno = new CadernoEntity();
        caderno.setId(id);
        caderno.setNome(cadernoRequest.getNome());
        caderno.setUsuario(cadernoUsuario);
        cadernoRepository.save(caderno);

        return new CadernoResponse(caderno.getId(), caderno.getNome(),
                caderno.getUsuario().getId());
    }

    @Override
    public void excluir(Long id) {
        CadernoEntity caderno = cadernoRepository.findById(id)
                .orElseThrow(()-> {
                    log.error("404 NOT FOUND -> Caderno não encontrado com id: {}" , id);
                    return new NotFoundException("Caderno não encontrado com id:" + id);}); // Verifica se o Caderno existe antes de excluir.
        cadernoRepository.delete(caderno);
    }
}
