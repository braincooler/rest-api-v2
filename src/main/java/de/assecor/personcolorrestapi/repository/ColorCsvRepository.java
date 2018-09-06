package de.assecor.personcolorrestapi.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import de.assecor.personcolorrestapi.entity.ColorEntity;
import de.assecor.personcolorrestapi.repository.interfaces.ColorRepository;

@Repository @Qualifier("ColorCsvRepository")
public class ColorCsvRepository implements ColorRepository{

	private List<ColorEntity> colors = new ArrayList<>();
	
	public Optional<ColorEntity> getColorEntityByColor(String color) {
		Optional<ColorEntity> result = colors.stream()
				.filter(c -> c.getColor().equals(color))
				.findFirst();
		return result;
	}
	
	@PostConstruct
	private void initColorData() {
		colors.add(new ColorEntity(1L, "Blau"));
		colors.add(new ColorEntity(2L, "Grün"));
		colors.add(new ColorEntity(3L, "Lila"));
		colors.add(new ColorEntity(4L, "Rot"));
		colors.add(new ColorEntity(5L, "Zitronengelb"));
		colors.add(new ColorEntity(6L, "Türkis"));
		colors.add(new ColorEntity(7L, "Weiß"));		
	}

	public Optional<ColorEntity> findById(Long id) {
		return colors.stream().filter(c -> c.getId() == id).findFirst();
	}
}
