package com.rivaelrodriguesjr.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rivaelrodriguesjr.cursomc.domains.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
