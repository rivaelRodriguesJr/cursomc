package com.rivaelrodriguesjr.cursomc.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rivaelrodriguesjr.cursomc.domains.Categoria;
import com.rivaelrodriguesjr.cursomc.repositories.CategoriaRepository;
import com.rivaelrodriguesjr.cursomc.services.exception.ObjectNotFoundException;

class CategoriaServiceTest {

	@InjectMocks
	CategoriaService service;

	@Mock
	CategoriaRepository repo;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testFind() {
		Optional<Categoria> cat1 = Optional.of(new Categoria(null, "Camping"));

		when(repo.findById(anyInt())).thenReturn(cat1);

		Categoria cat2 = service.find(1);

		assertNotNull(cat2);
		assertEquals("Camping", cat2.getNome());
	}

	@Test // (expected = ObjectNotFoundException.class)
	void testFind_ObjectNotFoundException() {
		when(repo.findById(anyInt())).thenReturn(Optional.empty());
		
		assertThrows(ObjectNotFoundException.class, () -> {service.find(1);});
	}
}
