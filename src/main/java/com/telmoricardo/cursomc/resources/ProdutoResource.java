package com.telmoricardo.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telmoricardo.cursomc.domain.Categoria;
import com.telmoricardo.cursomc.domain.Produto;
import com.telmoricardo.cursomc.dto.CategoriaDTO;
import com.telmoricardo.cursomc.dto.ProdutoDTO;
import com.telmoricardo.cursomc.resources.utils.URL;
import com.telmoricardo.cursomc.service.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;
	
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Produto obj = produtoService.find(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "nome", defaultValue = "") String nome, 
			@RequestParam(value = "categorias", defaultValue = "") String categorias, 
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "size", defaultValue = "10") Integer size, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC")String direction) {
		String nomeDecode = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> list = produtoService.search(nomeDecode, ids, page, size, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj-> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDto);
	} 
	
}
