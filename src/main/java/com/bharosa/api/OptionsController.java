package com.bharosa.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OptionsController {

	private static final Logger logger = LoggerFactory.getLogger(OptionsController.class);

	
	@RequestMapping(method = RequestMethod.OPTIONS, value="/*")
	public ResponseEntity<?> handleOptions() {
		logger.info("HANDLING OPTIONS REQUEST");
		return (ResponseEntity<?>) ResponseEntity.ok();
	}	

}
