package br.com.stoom.end;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco")

public class EnderecoController {

	private EnderecoRepository repository;
	
	EnderecoController(EnderecoRepository enderecoRepository) {
		this.repository = enderecoRepository;
	}
	
	@GetMapping
	public List findAll() {
		return repository.findAll();
	}
	
	@GetMapping(path = {"/id"})
	public ResponseEntity findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record)).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Endereco create(@RequestBody Endereco endereco) {
		return repository.save(endereco);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity update(@PathVariable("id") long id, @RequestBody Endereco endereco) {
		return repository.findById(id)
				.map(record -> {
					record.setStreetName(endereco.getStreetName());
					record.setNumber(endereco.getNumber());
					record.setComplement(endereco.getComplement());
					record.setNeighbourhood(endereco.getNeighbourhood());
					record.setCity(endereco.getCity());
					record.setState(endereco.getState());
					record.setCountry(endereco.getCountry());
					record.setZipcode(endereco.getZipcode());
					record.setLatitude(endereco.getLatitude());
					record.setLongitude(endereco.getLongitude());
					Endereco updated = repository.save(record);
					return ResponseEntity.ok().body(updated);
					
				}).orElse(ResponseEntity.notFound().build());
	
	}
	
	@DeleteMapping(path={"/{id}"})
	public ResponseEntity<?> delete(@PathVariable long id) {
		
		return repository.findById(id)
				.map(record -> {
					repository.deleteById(id);
					return ResponseEntity.ok().build();
				}).orElse(ResponseEntity.notFound().build());
	}
}
