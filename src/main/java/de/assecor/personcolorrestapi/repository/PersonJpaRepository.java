package de.assecor.personcolorrestapi.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.assecor.personcolorrestapi.entity.PersonEntity;
import de.assecor.personcolorrestapi.repository.interfaces.PersonRepository;

@Repository @Qualifier("PersonJpaRepository")
public interface PersonJpaRepository extends PersonRepository, JpaRepository<PersonEntity, Long> {

	List<PersonEntity> findByColorId(Long colorId);
}
