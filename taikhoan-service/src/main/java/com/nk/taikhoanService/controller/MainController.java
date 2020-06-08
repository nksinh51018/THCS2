package com.nk.taikhoanService.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nk.taikhoanService.dto.DanhSachTaiKhoanDTO;
import com.nk.taikhoanService.dto.TaiKhoanDTO;
import com.nk.taikhoanService.dto.TimKiemTaiKhoanDTO;
import com.nk.taikhoanService.service.AuthService;
import com.nk.taikhoanService.service.TaiKhoanService;

@RestController
public class MainController {

	@Autowired
	private TaiKhoanService taiKhoanService;
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/taiKhoan")
	public String themTaiKhoan(@RequestBody TaiKhoanDTO taiKhoanDTO,HttpServletResponse res) {
		System.out.println(taiKhoanDTO);
		String s = taiKhoanService.themTaiKhoan(taiKhoanDTO);
		System.out.println(s);
		return s;
	}
	
}
