package de.assecor.personcolorrestapi.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.assecor.personcolorrestapi.entity.ColorEntity;

@Repository @Qualifier("ColorJpaRepository")
public interface ColorJpaRepository extends JpaRepository<ColorEntity, Long> {

	ColorEntity getColorEntityByColor(String color);
}
