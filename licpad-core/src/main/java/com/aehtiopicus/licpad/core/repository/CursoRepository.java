package com.aehtiopicus.licpad.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aehtiopicus.licpad.core.domain.Cursos;

@Repository
public interface CursoRepository extends JpaRepository<Cursos, Long>{

}
