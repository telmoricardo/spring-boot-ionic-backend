package com.telmoricardo.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.telmoricardo.cursomc.domain.Categoria;
import com.telmoricardo.cursomc.repositories.CategoriaRepository;
import com.telmoricardo.cursomc.service.exceptions.DataIntegrityException;
import com.telmoricardo.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encotnrado Id: " + id + ", Tipo: " + Categoria.class 
				));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return categoriaRepository.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {				
			categoriaRepository.deleteById(id);		
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir categoria que possui produto");
		}
	}
}
