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
import org.springframework.web.bind.annotation.RequestParam;
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
	public List<City> getAll (){
		return cityRepo.findAll();
	}

	@PostMapping ("/save")
	public ResponseEntity<City> save (@RequestBody City city, HttpServletResponse response) {
		City c = cityRepo.save(city);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentServletMapping()
					.path("/{id}")
					.buildAndExpand(c.getId())
					.toUri();

		return ResponseEntity.created(uri).body(c);
	}
	
	@GetMapping ("/{id}")
	public City findById (@PathVariable Long id) {
		return cityRepo.getOne(id);
	}
	
	@GetMapping ("list/firstLetter/{letter}")
	public List<City> findByNameFirstLetter (@PathVariable String letter) {
		return cityRepo.findAllByNameIsStartingWith(letter);
	}
	
	@GetMapping ("find")
	public City findLatitudeLongitude (@RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude) {
		return cityRepo.findLatitudeLongitude(latitude, longitude);
	}
}