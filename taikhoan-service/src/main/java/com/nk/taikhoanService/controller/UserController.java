package com.nk.taikhoanService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nk.taikhoanService.dto.MatKhauDTO;
import com.nk.taikhoanService.dto.TaiKhoanDTO;
import com.nk.taikhoanService.service.TaiKhoanService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private TaiKhoanService taiKhoanService;
	
	@GetMapping("/ten")
	public String getTenDangNhap() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
	@GetMapping("/taiKhoan")
	public TaiKhoanDTO getTaiKhoan() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return taiKhoanService.getTaiKhoanByTenDangNhap(authentication.getName());
	}
	
	@PutMapping("/taiKhoan")
	public boolean suaTaiKhoan(@RequestBody TaiKhoanDTO taiKhoanDTO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!authentication.getName().equals(taiKhoanDTO.getTenDangNhap())) {
			return false;
		}
		return taiKhoanService.suaTaiKhoan(taiKhoanDTO);
	}
	
	@PutMapping("/taiKhoan/matKhau")
	public boolean thayDoiMatKhau(@RequestBody MatKhauDTO matKhauDTO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.getName());
		System.out.println(matKhauDTO.toString());
		return taiKhoanService.thayDoiMatKhau(matKhauDTO.getMatkhauCu(), matKhauDTO.getMatKhauMoi(), authentication.getName());
	}
	
	@GetMapping(path = "/id",consumes = MediaType.ALL_VALUE)
	public int getIdByTenDangNhap() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int a = taiKhoanService.getIdByTenDangNhap(authentication.getName());
		System.out.println(a);
		return a;
	}
}
