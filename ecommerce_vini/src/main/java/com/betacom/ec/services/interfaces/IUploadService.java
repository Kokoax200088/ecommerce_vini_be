package com.betacom.ec.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {
	String saveImage(MultipartFile file, Integer id) throws Exception;
	String buildUrl(String fileName);
}
