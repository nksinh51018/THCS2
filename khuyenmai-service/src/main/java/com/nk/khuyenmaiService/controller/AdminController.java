package com.nk.khuyenmaiService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nk.khuyenmaiService.dto.DanhSachIdSanPham;
import com.nk.khuyenmaiService.dto.DanhSachKhuyenMaiDTO;
import com.nk.khuyenmaiService.dto.KhuyenMaiDTO;
import com.nk.khuyenmaiService.service.ChiTietKhuyenMaiService;
import com.nk.khuyenmaiService.service.KhuyenMaiService;

@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private KhuyenMaiService khuyenMaiService;
	
	@Autowired
	private ChiTietKhuyenMaiService chiTietKhuyenMaiService;
	
	@GetMapping("/khuyenMai/{n}")
	public DanhSachKhuyenMaiDTO getAllKhuyenMai(@PathVariable int n) {
		return khuyenMaiService.getAllKhuyenMai(n*12);
	}
	
	@GetMapping("/khuyenMai/timKiem/{a}/{n}")
	public DanhSachKhuyenMaiDTO findKhuyenMai(@PathVariable int n,@PathVariable int a) {
		return khuyenMaiService.findKhuyenMai(a, n*12);
	}
	
	@GetMapping("/khuyenMai/ten/{tenKhuyenMai}/{n}")
	public DanhSachKhuyenMaiDTO findKhuyenMaiByTenKhuyenMai(@PathVariable int n,@PathVariable String tenKhuyenMai) {
		return khuyenMaiService.findKhuyenMaiByTenKhuyenMai(tenKhuyenMai, n*12);
	}
	
	@GetMapping("/khuyenMai/id/{id}")
	public KhuyenMaiDTO getKhuyenMaiById(@PathVariable int id) {
		return khuyenMaiService.getKhuyenMaiById(id);
	}
	
	@GetMapping("/chiTietKhuyenMai/{id}")
	public DanhSachIdSanPham getListIdSanPhamByIdKhuyenMai(@PathVariable int id) {
		return chiTietKhuyenMaiService.getListIdSanPhamByIdKhuyenMai(id);
	}
	
	@PutMapping("/khuyenMai")
	public boolean suaKhuyenMai(@RequestBody KhuyenMaiDTO khuyenMaiDTO) {
		return khuyenMaiService.suaKhuyenMai(khuyenMaiDTO);
	}
	
	@PostMapping("/khuyenMai")
	public boolean themKhuyenMai(@RequestBody KhuyenMaiDTO khuyenMaiDTO) {
		return khuyenMaiService.themKhuyenMai(khuyenMaiDTO);
	}
	
	@DeleteMapping("/khuyenMai/{id}")
	public boolean suaKhuyenMai(@PathVariable int id) {
		return khuyenMaiService.xoaKhuyenMai(id);
	}
}
