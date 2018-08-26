package de.assecor.personcolorrestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.assecor.personcolorrestapi.entity.PersonEntity;

@Repository
public interface PersonJpaRepository extends JpaRepository<PersonEntity, Long> {

	List<PersonEntity> findByColorId(Long colorId);
}
