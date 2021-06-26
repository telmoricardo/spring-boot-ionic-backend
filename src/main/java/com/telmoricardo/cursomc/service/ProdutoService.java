package com.telmoricardo.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.telmoricardo.cursomc.domain.Categoria;
import com.telmoricardo.cursomc.domain.Produto;
import com.telmoricardo.cursomc.repositories.CategoriaRepository;
import com.telmoricardo.cursomc.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id) {
		Optional<Produto> obj = produtoRepository.findById(id);
		return obj.orElse(null);
	}
	
	public Page<Produto> search(String nome, List<Integer> ids,Integer page, Integer size, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy );
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return produtoRepository.search(nome, categorias, pageRequest);
	}
 
}
