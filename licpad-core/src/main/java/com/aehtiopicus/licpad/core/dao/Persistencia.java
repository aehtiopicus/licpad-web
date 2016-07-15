package com.aehtiopicus.licpad.core.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aehtiopicus.licpad.core.domain.Cursos;
import com.aehtiopicus.licpad.core.domain.Persona;

@Service
@Repository
public class Persistencia {

	 
	 private EntityManagerFactory entityManagerFactory;
	 
	 @PersistenceContext
	 private EntityManager em ;
	 
	 @PersistenceUnit(unitName = "entityManagerFactory")
	 public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
		this.entityManagerFactory = entityManagerFactory;
		this.em = entityManagerFactory.createEntityManager();
	 }
	 

	
@Transactional
	public Cursos saveCurso(){
		Cursos c = new Cursos();
		em.persist(c);
		return c;
	}
@Transactional	
	public void savePersona(Cursos c){
		Persona p = new Persona();
		p.setCurso(em.find(Cursos.class,c.getId()));
		em.persist(p);		
		em.createQuery("SELECT c FROM Persona c").getResultList();
		System.out.println("aaa");
	}
}
