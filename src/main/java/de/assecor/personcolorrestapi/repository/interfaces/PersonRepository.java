package de.assecor.personcolorrestapi.repository.interfaces;

import java.util.List;
import java.util.Optional;

import de.assecor.personcolorrestapi.entity.PersonEntity;

public interface PersonRepository {

	List<PersonEntity> findAll();
	Optional<PersonEntity> findById(Long personId);
	List<PersonEntity> findByColorId(Long colorId);
	PersonEntity save(PersonEntity personEntity);
}
