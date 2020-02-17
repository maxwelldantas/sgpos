package br.com.sgpos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sgpos.model.Estoque;
import br.com.sgpos.repository.EstoqueRepository;

@RestController
@RequestMapping({ "/estoque" })
public class EstoqueController {

	private EstoqueRepository repository;

	EstoqueController(EstoqueRepository estoqueRepository) {
		this.repository = estoqueRepository;
	}

	@GetMapping
	public List<Estoque> findAll() {
		return repository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Estoque> findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Estoque create(@RequestBody Estoque estoque) {
		return repository.save(estoque);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Estoque> update(@PathVariable("id") long id, @RequestBody Estoque estoque) {
		return repository.findById(id).map(record -> {
			record.setProduto(estoque.getProduto());
			record.setDescricao(estoque.getDescricao());
			Estoque updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
