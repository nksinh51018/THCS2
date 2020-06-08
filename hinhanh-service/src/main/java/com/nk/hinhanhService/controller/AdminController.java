package com.nk.hinhanhService.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nk.hinhanhService.DTO.HinhAnhDTO;
import com.nk.hinhanhService.service.FileStorageService;
import com.nk.hinhanhService.service.HinhAnhService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

	@Autowired
	private HinhAnhService hinhAnhService;
	
	@Autowired
    private FileStorageService fileStorageService;

	 @PostMapping("/uploadFile")
	    public boolean uploadFile(@RequestParam("file") MultipartFile file,HttpServletResponse response) {
	    	String type = file.getContentType();
	    	if(type.equals("image/png") || type.equals("image/jpeg")) {
	    		fileStorageService.storeFile(file);
	        	
	        	//response.addHeader("Access-Control-Allow-Origin", "*");
	            return true;
	    	}
	    	else {
	    		return false;
	    	}
	    	
	    	
	    }
	
	@PutMapping("/sanPham")
	public boolean suaHinhAnh(@RequestBody HinhAnhDTO hinhAnhDTO) {
		return hinhAnhService.suaHinhAnh(hinhAnhDTO);
	}
	
	@PostMapping("/sanPham")
	public boolean themHinhAnh(@RequestBody HinhAnhDTO hinhAnhDTO) {
		System.out.println(hinhAnhDTO.toString());
		return hinhAnhService.themHinhAnh(hinhAnhDTO);
	}
	
	@DeleteMapping("/sanPham/{id}")
	public boolean xoaHinhAnh(@PathVariable int id) {
		hinhAnhService.xoaHinhAnh(id);
		return true;
	}
	
}
