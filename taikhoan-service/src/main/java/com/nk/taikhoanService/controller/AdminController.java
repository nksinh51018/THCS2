package com.nk.taikhoanService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nk.taikhoanService.dto.DanhSachTaiKhoanDTO;
import com.nk.taikhoanService.dto.TaiKhoanDTO;
import com.nk.taikhoanService.dto.TimKiemTaiKhoanDTO;
import com.nk.taikhoanService.service.TaiKhoanService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private TaiKhoanService taiKhoanService;
	
	@GetMapping("/ten")
	public String getTenDangNhap() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
	@GetMapping("/taiKhoan/{n}")
	public DanhSachTaiKhoanDTO getAllTaiKhoan(@PathVariable int n) {
		return taiKhoanService.getAllTaiKhoan(n*12);
	}
	

	@PostMapping("/taiKhoan/timKiem/{n}")
	public DanhSachTaiKhoanDTO findTaiKhoan(@RequestBody TimKiemTaiKhoanDTO timKiemTaiKhoanDTO,@PathVariable int n) {
		return taiKhoanService.findTaiKhoan(timKiemTaiKhoanDTO, n*12);
	}
	
	@PostMapping("/taiKhoan/timKiem/{tenDangNhap}/{n}")
	public DanhSachTaiKhoanDTO findTaiKhoanByTenDangNhap(@PathVariable String tenDangNhap,@PathVariable int n) {
		return taiKhoanService.findTaiKhoanByTenDangNhap(tenDangNhap, n*12);
	}
	
	@GetMapping("/taiKhoan/id/{id}")
	public TaiKhoanDTO getTaiKhoanById(@PathVariable int id) {
		return taiKhoanService.getTaiKhoanById(id);
	}
	
	@PutMapping("/taiKhoan")
	public boolean suaTaiKhoan(@RequestBody TaiKhoanDTO taiKhoanDTO) {
		return taiKhoanService.suaTaiKhoan(taiKhoanDTO);
	}
	
}
