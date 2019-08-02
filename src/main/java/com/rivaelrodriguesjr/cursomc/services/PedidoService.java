package com.rivaelrodriguesjr.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rivaelrodriguesjr.cursomc.domains.Pedido;
import com.rivaelrodriguesjr.cursomc.repositories.PedidoRepository;
import com.rivaelrodriguesjr.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

 	@Autowired
	private PedidoRepository repo;

 	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

 }