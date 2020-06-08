package com.nk.sanphamService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nk.sanphamService.DTO.ChiTietSanPhamDTO;
import com.nk.sanphamService.DTO.DanhSachChiTietSanPhamDTO;
import com.nk.sanphamService.DTO.DanhSachSanPhamAdmin;
import com.nk.sanphamService.DTO.DanhSachSanPhamDTO;
import com.nk.sanphamService.DTO.SanPhamDTO;
import com.nk.sanphamService.DTO.TimKiemDTO;
import com.nk.sanphamService.service.ChiTietSanPhamService;
import com.nk.sanphamService.service.HinhAnhService;
import com.nk.sanphamService.service.SanPhamService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private SanPhamService sanPhamService;
	
	@Autowired
	private HinhAnhService hinhAnhService;
	
	@Autowired
	private ChiTietSanPhamService chiTietSanPhamService;
	
	@GetMapping("/sanPham")
	public DanhSachSanPhamAdmin getAllSanPham() {
		return sanPhamService.findSanPham();
	}
	
	@PostMapping("/timKiem/{n}")
	public DanhSachSanPhamDTO findSanPham(@RequestBody TimKiemDTO timKiemDTO,@PathVariable int n) {
		int t = n*12;
		System.out.println(timKiemDTO);
		return sanPhamService.findSanPham(timKiemDTO, t);
	}
	
	@PostMapping("/sanPham")
	public boolean themSanPham(@RequestBody SanPhamDTO sanPhamDTO) {
		System.out.println(sanPhamDTO.toString());
		int kt1 = sanPhamService.themSanPham(sanPhamDTO);
		
		boolean kt2 = hinhAnhService.themHinhAnh(sanPhamDTO.getHinhAnhDTOs(), kt1);
		return  kt2;
	}
	
	@PutMapping("/sanPham")
	public boolean suaSanPham(@RequestBody SanPhamDTO sanPhamDTO) {
		System.out.println(sanPhamDTO.toString());
		boolean kt1 = sanPhamService.suaSanPham(sanPhamDTO);
		
		boolean kt2 = hinhAnhService.suaHinhAnh(sanPhamDTO.getHinhAnhDTOs(), sanPhamDTO.getId());
		return  kt1 && kt2;
	}
	
	@GetMapping("/sanPham/{id}")
	public SanPhamDTO getSanPham(@PathVariable int id) {
		return sanPhamService.getSanPhamByIDAdmin(id);
	}
	
	@GetMapping("/chiTiet/{n}")
	public DanhSachChiTietSanPhamDTO getAllChiTiet(@PathVariable int n) {

		return chiTietSanPhamService.getAllChiTiet(n*12);
	}
	
	@PostMapping("/hoaDon")
	public boolean huyHoaDon(@RequestBody ChiTietSanPhamDTO chiTietSanPhamDTO) {
		return chiTietSanPhamService.huyHoaDon(chiTietSanPhamDTO);
	}
}


