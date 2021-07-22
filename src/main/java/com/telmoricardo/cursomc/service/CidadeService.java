package com.telmoricardo.cursomc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telmoricardo.cursomc.domain.Cidade;
import com.telmoricardo.cursomc.repositories.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public List<Cidade> findCidades(Integer estadoId){
		return cidadeRepository.findCidades(estadoId);
	}

}
