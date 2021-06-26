package com.telmoricardo.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telmoricardo.cursomc.domain.Pagamento;
import com.telmoricardo.cursomc.repositories.PagamentoRepository;
import com.telmoricardo.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class PagamentoService {
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public Pagamento find(Integer id) {
		Optional<Pagamento> obj = pagamentoRepository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encotnrado Id: " + id + ", Tipo: " + Pagamento.class 
				));
	}

	public Pagamento insert(Pagamento obj) {
		obj.setId(null);
		return pagamentoRepository.save(obj);
	}

	
}
