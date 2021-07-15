package com.telmoricardo.cursomc.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.telmoricardo.cursomc.service.exceptions.FileException;

@Service
public class S3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3client;

	@Value("${s3.bucket}")
	private String bucketName;

	public URI uploadFile(MultipartFile multipartFile) {
		try {
			String filename = multipartFile.getOriginalFilename();
			InputStream is = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();
			return upload(filename, is, contentType);

		} catch (IOException e) {
			throw new FileException("Error IO " + e.getMessage());
		}

	}

	public URI upload(String filename, InputStream is, String contentType) {
		try {
			ObjectMetadata objMeta = new ObjectMetadata();
			objMeta.setContentType(contentType);
			LOG.info("Iniciando upload");

			s3client.putObject(bucketName, filename, is, objMeta);
			LOG.info("Upload finalizado");
			return s3client.getUrl(bucketName, filename).toURI();
		} catch (URISyntaxException e) {
			throw new FileException("Error ao converter URL para URi");
		}
	}

}
