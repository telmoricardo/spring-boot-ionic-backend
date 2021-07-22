package com.telmoricardo.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.telmoricardo.cursomc.domain.Cliente;
import com.telmoricardo.cursomc.dto.ClienteDTO;
import com.telmoricardo.cursomc.dto.ClienteNewDTO;
import com.telmoricardo.cursomc.service.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Cliente obj = clienteService.find(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	@RequestMapping(value="/email", method = RequestMethod.GET)
	public ResponseEntity<?> findByEmail(@RequestParam(value="value") String email) {
		Cliente obj = clienteService.findByEmail(email);
		return ResponseEntity.ok().body(obj);		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDTO){
		Cliente obj = clienteService.fromDTO(objDTO);
		obj = clienteService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO objDTO){
		Cliente obj = clienteService.fromDTO(objDTO);
		obj.setId(id);
		obj = clienteService.update(obj);		
		
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> list = clienteService.findAll();
		List<ClienteDTO> listDto = list.stream().map(obj-> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "size", defaultValue = "10") Integer size, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC")String direction) {
		Page<Cliente> list = clienteService.findPage(page, size, orderBy, direction);
		Page<ClienteDTO> listDto = list.map(obj-> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/picture",method = RequestMethod.POST)
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile file){
		URI uri = clienteService.uploadProfilePicture(file);
		return ResponseEntity.created(uri).build();
	}
	
}
