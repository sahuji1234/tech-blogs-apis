package com.suraj.blogs.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.suraj.blogs.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String UploadImage(String path, MultipartFile file) throws IOException {
		
		//File name
		String name = file.getOriginalFilename();
		
		// random generate file
		String randomID = UUID.randomUUID().toString();
		String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));
		
		//Full path
		String filePath = path+File.separator+fileName1;
		
		
		
	//	create folder if not exist
		
		File f= new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		
		// file copy
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return fileName1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullpath =path+File.separator+fileName;
		InputStream is = new FileInputStream(fullpath);
		return is;
	}

}
