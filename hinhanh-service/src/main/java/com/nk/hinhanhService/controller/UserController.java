package com.nk.hinhanhService.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nk.hinhanhService.DTO.DanhSachHinhAnhDTO;
import com.nk.hinhanhService.service.FileStorageService;
import com.nk.hinhanhService.service.HinhAnhService;

@RestController
@CrossOrigin("*")
public class UserController {
 
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
	
	@GetMapping("/sanPham/{id}")
	public DanhSachHinhAnhDTO getHinhAnhByIdSanPham(@PathVariable int id){
		return hinhAnhService.getHinhAnhByIdSanPham(id);
	}
    
	@GetMapping("/sanPham/{idSanPham}/{idMau}")
	public String getHinhAnhByIdSanPham(@PathVariable int idSanPham,@PathVariable int idMau,HttpServletResponse response){
		//response.addHeader("Access-Control-Allow-Origin", "*");
		return hinhAnhService.getHinhAnhByIdSanPhamAndIdMau(idSanPham, idMau).getUrl();
	}
}