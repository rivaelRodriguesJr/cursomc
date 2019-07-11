package com.rivaelrodriguesjr.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rivaelrodriguesjr.cursomc.domains.Cliente;
import com.rivaelrodriguesjr.cursomc.repositories.ClienteRepository;
import com.rivaelrodriguesjr.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> busca = repo.findById(id);
		return busca.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

}
