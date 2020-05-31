package br.usjt.usjt_ccp3anmca_rest_json_cities.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.usjt.usjt_ccp3anmca_rest_json_cities.model.beans.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{
}