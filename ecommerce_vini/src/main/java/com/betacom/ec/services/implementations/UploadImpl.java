package com.betacom.ec.services.implementations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.services.interfaces.IUploadService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UploadImpl implements IUploadService{
	private final Path uploadPath;
	
	public UploadImpl(@Value("$app.upload.dir:uploads") String uploadDir) {
		this.uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
		init();
	}
	
	private void init() {
		try {
			if(Files.notExists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
		} catch (IOException e) {
			throw new EcommerceVinoException("upload_create_error");
		}
	}
	

	@Override
	public String saveImage(MultipartFile file, Integer id) throws Exception {
		Assert.isTrue(!file.isEmpty(), () -> "upload_empty");
		
		String uniqueName = buildFileName(file);
		Path destinationFile = uploadPath.resolve(uniqueName);
		try {
			Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
			//set image(uniqueName) dei nostri model e la save.
		} catch (IOException e) {
			throw new EcommerceVinoException("upload_save_error");
		}
		return null;
	}
	
	private String buildFileName(MultipartFile file) {
		String original = file.getOriginalFilename();
		String extension = "";
		String originalName = original.trim().replaceAll("\\s", "_");
		
		log.debug("original Name {}",originalName);
		
		extension = Optional.ofNullable(originalName)
				.filter(name -> name.contains("."))
				.map(name -> name.substring(name.lastIndexOf(".")))
				.orElse("");
		
		return originalName.substring(0, originalName.lastIndexOf(".")) + "-" + UUID.randomUUID().toString() + extension;
	}

	@Override
	public String buildUrl(String fileName) {
		return ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/images/")
				.path(fileName)
				.toUriString();
	}
	
}
