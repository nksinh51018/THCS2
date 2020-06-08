package com.nk.hoadonService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nk.hoadonService.dto.DanhSachHoaDonDTO;
import com.nk.hoadonService.dto.HoaDonDTO;
import com.nk.hoadonService.service.ChiTietSanPhamService;
import com.nk.hoadonService.service.HoaDonService;
import com.nk.hoadonService.service.TaiKhoanService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private HoaDonService hoaDonService;
	
	@Autowired
	private TaiKhoanService taiKhoanService;
	
	@Autowired
	private ChiTietSanPhamService chiTietSanPhamService;
	
	@PostMapping("/hoaDon")
	public boolean themHoaDon(@RequestBody HoaDonDTO hoaDonDTO) {
		System.out.println(hoaDonDTO.toString());
		return hoaDonService.themHoaDon(hoaDonDTO);
	}
	
	@GetMapping("/hoaDon/{n}")
	public DanhSachHoaDonDTO getHoaDonKhachHang(@PathVariable int n) {
		int t = n*12;
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
		return hoaDonService.getHoaDonByIdTaiKhoan(SecurityContextHolder.getContext().getAuthentication(), t);
		//return taiKhoanService.getIdTaiKhoanByTenDangNhap(SecurityContextHolder.getContext().getAuthentication());
	}
	
	@GetMapping("/hoaDon/id/{id}")
	public HoaDonDTO getHoaDonKhachHangById(@PathVariable int id) {
		return hoaDonService.getHoaDonByIdAndIdTaiKhoan(SecurityContextHolder.getContext().getAuthentication(), id);
	}
	
	@DeleteMapping("/hoaDon/id/{id}")
	public boolean xoaHoaDonKhachHangById(@PathVariable int id) {
		return hoaDonService.xoaHoaDonByIdAndIdTaiKhoan(id,SecurityContextHolder.getContext().getAuthentication());
	}
}
