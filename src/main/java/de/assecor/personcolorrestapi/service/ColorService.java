package de.assecor.personcolorrestapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import de.assecor.personcolorrestapi.repository.interfaces.ColorRepository;

@Service
public class ColorService {
	
	@Autowired @Qualifier("ColorCsvRepository")
	private ColorRepository colorRepository;
	
}
