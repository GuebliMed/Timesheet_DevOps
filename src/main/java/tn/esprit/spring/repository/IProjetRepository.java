package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entity.Projet;

public interface IProjetRepository extends CrudRepository<Projet, Integer> {

}
