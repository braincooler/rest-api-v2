package de.assecor.personcolorrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.assecor.personcolorrestapi.entity.ColorEntity;

@Repository
public interface ColorJpaRepository extends JpaRepository<ColorEntity, Long> {

	ColorEntity getColorEntityByColor(String color);

}
