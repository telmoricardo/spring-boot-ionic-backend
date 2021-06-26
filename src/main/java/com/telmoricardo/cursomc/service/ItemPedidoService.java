package com.telmoricardo.cursomc.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telmoricardo.cursomc.domain.ItemPedido;
import com.telmoricardo.cursomc.repositories.ItemPedidoRepository;
import com.telmoricardo.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class ItemPedidoService {
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public ItemPedido find(Integer id) {
		Optional<ItemPedido> obj = itemPedidoRepository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encotnrado Id: " + id + ", Tipo: " + ItemPedido.class 
				));
	}

	public ItemPedido insert(Set<ItemPedido> set) {
		((ItemPedido) set).setId(null);
		return (ItemPedido) itemPedidoRepository.saveAll(set);
	}

	
}
