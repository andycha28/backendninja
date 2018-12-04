package com.udemy.backendninja.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller

public class UploadController {
	
	//private static String UPLOADED_FOLDER = "C:\\Users\\achavarria\\Documents\\Projects_GIT\\";
	@Autowired
	private ResourceLoader resourceLoader;
	
	
	//private static String UPLOADED_FOLDER = "classpath:BulkFiles\";
	
	
	@GetMapping("/")
	public String index() 
	{
		return "uploadForm";
	}
	
	@PostMapping("/upload")
	public String FileUpload(@RequestAttribute("file") MultipartFile file, RedirectAttributes redirectAttributes)
	{
		Resource dir = resourceLoader.getResource("classpath:BulkFiles");
		
		if (file.isEmpty()) 
		{
			redirectAttributes.addFlashAttribute("message","Porfavor seleccione un archivo a cargar");
			return "redirect:uploadStatus";
			
		}
		try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(dir + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "Subio archivo con exito" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
		
		
	}
	@GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadForm";
    }
	
}


	

