package com.loe.dms.spring.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.loe.dms.spring.model.data.GalleriesInfo;
import com.loe.dms.spring.model.data.UserSession;
import com.loe.dms.spring.model.entity.GalleryDetails;
import com.loe.dms.spring.service.GalleriesService;
import com.loe.dms.spring.util.WebUtil;
import com.loe.dms.spring.validator.GalleriesInfoValidator;

@Controller
@RequestMapping("/galleries")
public class GalleriesController {
	private static final Logger logger = LoggerFactory.getLogger(GalleriesController.class);

	private GalleriesService galleriesService;

	@Autowired
	private GalleriesInfoValidator validator;
	
	@Autowired
	private ServletContext servletContext;

	@Autowired(required = true)
	@Qualifier(value = "galleriesService")
	public void setGalleriesService(GalleriesService galleriesService) {
		this.galleriesService = galleriesService;
	}

	@RequestMapping(value = "/gallery", method = RequestMethod.GET)
	public String loadGalleriesPage(HttpServletRequest request, Model model) {
		logger.info(">>>>> loadGalleriesPage >>>>");
		model.addAttribute("userSession", WebUtil.getUserSession(request));
		GalleriesInfo galleriesInfo = new GalleriesInfo();
		model.addAttribute("galleriesInfo", galleriesInfo);
		return "galleries";
	}

	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/saveuploadedfile", method = RequestMethod.POST)
	public String saveUploadedFileHandler(HttpServletRequest request, Model model, @ModelAttribute("galleriesInfo") GalleriesInfo galleriesInfo, BindingResult result) {
		logger.info(">>>saveUploadedFileHandler>>>");
		model.addAttribute("userSession", WebUtil.getUserSession(request));
		MultipartFile file = galleriesInfo.getFile();
		// Validation code
		validator.validate(galleriesInfo, result);
		// Check validation errors
		if (result.hasErrors()) {
			return "galleries";
		}
		if (!file.isEmpty()) {
			try {
				GalleryDetails galleryDetails = new GalleryDetails();
				galleryDetails.setUser_id(WebUtil.getUserSession(request).getUser_id());
				galleryDetails.setFile_name(file.getOriginalFilename());
				galleryDetails.setFile_data(file.getBytes());
				galleriesService.saveFile(galleryDetails);
			} catch (Exception e) {
				logger.error(e.getMessage()+e);
			}
		}

		return "redirect:/activities/activity";
	}

	@RequestMapping(value = "/imageDisplay/{user_id}", method = RequestMethod.GET)
	public void showImage(@PathVariable("user_id") String user_id, HttpServletResponse response, HttpServletRequest request, Model model) {
		try {
			GalleryDetails galleryDetails = galleriesService.getFileById(user_id, "1");
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			if (galleryDetails != null) {
				response.getOutputStream().write(galleryDetails.getFile_data());
			} else {
				InputStream in = servletContext.getResourceAsStream("/resources/images/default_photo.jpg");
				response.getOutputStream().write(IOUtils.toByteArray(in));
			}
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody String uploadFileHandler(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location=" + serverFile.getAbsolutePath());

				return "You successfully uploaded file=" + name;
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + name + " because the file was empty.";
		}
	}

	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody String uploadMultipleFileHandler(@RequestParam("name") String[] names, @RequestParam("file") MultipartFile[] files) {

		if (files.length != names.length)
			return "Mandatory information missing";

		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String name = names[i];
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location=" + serverFile.getAbsolutePath());

				message = message + "You successfully uploaded file=" + name + "<br />";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}

}
