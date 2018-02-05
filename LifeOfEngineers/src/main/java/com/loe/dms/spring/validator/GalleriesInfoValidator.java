package com.loe.dms.spring.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.loe.dms.spring.model.data.GalleriesInfo;

@Component
public class GalleriesInfoValidator implements Validator {

	private static final Logger logger = LoggerFactory.getLogger(GalleriesInfoValidator.class);

	public boolean supports(Class clazz) {
		return GalleriesInfo.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		logger.info(">>>validate>>>");

		GalleriesInfo galleriesInfo = (GalleriesInfo) target;
		MultipartFile file = galleriesInfo.getFile();

		if (file.isEmpty()) {
			errors.rejectValue("file", "", "Please select the file to be uploaded");
		}
		
		if(file.getSize()>2097152){
			errors.rejectValue("file", "", "The selcted file size is more than 2MB,compress or select less file");
		}

	}

}