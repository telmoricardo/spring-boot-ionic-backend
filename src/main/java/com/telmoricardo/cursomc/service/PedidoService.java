package com.telmoricardo.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telmoricardo.cursomc.domain.Pedido;
import com.telmoricardo.cursomc.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository categoriaRepository;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = categoriaRepository.findById(id);
		return obj.orElse(null);
	}
}
