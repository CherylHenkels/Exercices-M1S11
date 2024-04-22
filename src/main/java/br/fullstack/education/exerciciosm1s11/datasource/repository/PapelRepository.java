package br.fullstack.education.exerciciosm1s11.datasource.repository;

import br.fullstack.education.exerciciosm1s11.datasource.entity.PapelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface PapelRepository extends JpaRepository<PapelEntity,Long> {
    Optional<PapelEntity> findByNome(String nome);
}
