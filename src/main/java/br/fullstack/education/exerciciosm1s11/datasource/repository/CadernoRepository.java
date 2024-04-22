package br.fullstack.education.exerciciosm1s11.datasource.repository;

import br.fullstack.education.exerciciosm1s11.datasource.entity.CadernoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadernoRepository extends JpaRepository<CadernoEntity, Long> {
}
