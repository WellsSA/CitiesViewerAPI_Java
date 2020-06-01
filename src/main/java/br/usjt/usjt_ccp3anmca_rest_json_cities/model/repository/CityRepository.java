package br.usjt.usjt_ccp3anmca_rest_json_cities.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.usjt.usjt_ccp3anmca_rest_json_cities.model.beans.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{
	
	public List<City> findAllByNameIsStartingWith(String letter);
	
	
	@Query("SELECT entity FROM City entity WHERE entity.latitude = :latitude AND entity.longitude = :longitude")
    public City findLatitudeLongitude(Double latitude, Double longitude);
	// ou: public City findByLatitudeAndLongitude(Double latitude, Double longitude);
	
}