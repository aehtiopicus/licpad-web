package com.aehtiopicus.licpad.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aehtiopicus.licpad.core.domain.Cursos;
import com.aehtiopicus.licpad.core.repository.CursoRepository;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository repo;
	
	public void saveCurso(Cursos c){
		repo.save(c);
	}
	
	public List<Cursos> getCurso(){
		return repo.findAll();
	}

}
