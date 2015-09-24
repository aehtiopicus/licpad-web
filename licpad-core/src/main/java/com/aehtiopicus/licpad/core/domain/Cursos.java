package com.aehtiopicus.licpad.core.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

@Entity
public class Cursos implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "NE_IDGEN")
    @TableGenerator(name = "NE_IDGEN", table = "NEWENTITY_KEYGEN",initialValue=1,allocationSize=1)
	private Long id;
	
	@OneToMany(mappedBy="curso", cascade = CascadeType.ALL)
	private List<Persona> persona;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Persona> getPersona() {
		return persona;
	}

	public void setPersona(List<Persona> persona) {
		this.persona = persona;
	}
	
	
}
