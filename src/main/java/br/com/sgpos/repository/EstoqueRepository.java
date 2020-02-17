package br.com.sgpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sgpos.model.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
	
}
