package de.assecor.personcolorrestapi.repository.interfaces;

import java.util.Optional;

import de.assecor.personcolorrestapi.entity.ColorEntity;

public interface ColorRepository {

	Optional<ColorEntity> getColorEntityByColor(String color);
}
