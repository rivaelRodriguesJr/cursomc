package com.rivaelrodriguesjr.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rivaelrodriguesjr.cursomc.domains.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}
