package com.aehtiopicus.licpad.core.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

@Entity
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "NE_IDGEN")
    @TableGenerator(name = "NE_IDGEN", table = "NEWENTITY_KEYGEN",initialValue=1,allocationSize=1)
	private Long id;
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private Cursos curso;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cursos getCurso() {
		return curso;
	}
	public void setCurso(Cursos curso) {
		this.curso = curso;
	}
	
	
	
	
}
