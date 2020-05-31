package br.usjt.usjt_ccp3anmca_rest_json_cities.model.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.usjt.usjt_ccp3anmca_rest_json_cities.model.beans.City;
import br.usjt.usjt_ccp3anmca_rest_json_cities.model.repository.CityRepository;

@RestController
@RequestMapping ("/cities")
public class CityResource {
	@Autowired
	private CityRepository cityRepo;
	
	@GetMapping ("/list")
	public List<City> todosOsLivros (){
		return cityRepo.findAll();
	}

	@PostMapping ("/save")
	//@ResponseStatus (HttpStatus.CREATED)
	public ResponseEntity<City> salvar (@RequestBody City city, HttpServletResponse response) {
		City c = cityRepo.save(city);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentServletMapping()
					.path("/{id}")
					.buildAndExpand(c.getId())
					.toUri();

		// response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(c);
	}
	
	@GetMapping ("/{id}")
	public City buscarPeloId (@PathVariable Long id) {
		return cityRepo.getOne(id);
	}
}