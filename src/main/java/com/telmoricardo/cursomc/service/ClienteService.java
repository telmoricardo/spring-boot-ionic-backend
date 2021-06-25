package com.telmoricardo.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.telmoricardo.cursomc.domain.Cliente;
import com.telmoricardo.cursomc.dto.ClienteDTO;
import com.telmoricardo.cursomc.repositories.ClienteRepository;
import com.telmoricardo.cursomc.service.exceptions.DataIntegrityException;
import com.telmoricardo.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encotnrado Id: " + id + ", Tipo: " + Cliente.class 
				));
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return clienteRepository.save(obj);
	}

	public Cliente update(Cliente obj) {
		Cliente cliente = find(obj.getId());
		cliente.setNome(obj.getNome());
		cliente.setEmail(obj.getEmail());
		return clienteRepository.save(cliente);
	}

	public void delete(Integer id) {
		find(id);
		try {				
			clienteRepository.deleteById(id);		
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir cliente que possui produto");
		}
	}

	public List<Cliente> findAll() {		
		return clienteRepository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer size, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy );
		return clienteRepository.findAll(pageRequest);
		
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
}
