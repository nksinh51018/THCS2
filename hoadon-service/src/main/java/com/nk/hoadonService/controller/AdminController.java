package com.nk.hoadonService.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nk.hoadonService.dto.DanhSachChiTietSanPhamTheoDoanhThuDTO;
import com.nk.hoadonService.dto.DanhSachHoaDonDTO;
import com.nk.hoadonService.dto.DanhSachTongTienThangDTO;
import com.nk.hoadonService.dto.DoanhThuNgayDTO;
import com.nk.hoadonService.dto.HoaDonDTO;
import com.nk.hoadonService.dto.TimKiemHoaDonDTO;
import com.nk.hoadonService.dto.TrangThaiDTO;
import com.nk.hoadonService.service.ChiTietHoaDonService;
import com.nk.hoadonService.service.HoaDonService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private HoaDonService hoaDonService;
	
	@Autowired
	private ChiTietHoaDonService chiTietHoaDonService;
	
	@GetMapping("/hoaDon/{n}")
	public DanhSachHoaDonDTO getAllHoaDon(@PathVariable int n) {
		int t = n*12;
		return hoaDonService.getAllHoaDon(t);
	}
	
	@PostMapping("/hoaDon/timKiem/{n}")
	public DanhSachHoaDonDTO findHoaDon(@PathVariable int n,@RequestBody TimKiemHoaDonDTO timKiemHoaDonDTO) {
		int t = n*12;
		System.out.println(timKiemHoaDonDTO.toString());
		System.out.println(timKiemHoaDonDTO.getNgayBatDau());
		return hoaDonService.findHoaDon(timKiemHoaDonDTO,t);
	}
	
	@GetMapping("/hoaDon/id/{id}")
	public HoaDonDTO getHoaDonById(@PathVariable int id) {
		return hoaDonService.getHoaDonById(id);
	}
	
	@PutMapping("/hoaDon/trangThai")
	public boolean thayDoiTrangThaiHoaDon(@RequestBody TrangThaiDTO trangThaiDTO) {
		return hoaDonService.thayDoiTrangThaiDoaDon(trangThaiDTO.getTrangThai(),trangThaiDTO.getId());
	}
	
	@GetMapping("/taiKhoan/tien/{id}")
	public int getTongTienByIdTaiKhoan(@PathVariable int id) {
		return hoaDonService.getTongTienByIdTaiKhoan(id);
	}
	
	@GetMapping("/taiKhoan/{id}/{n}")
	public DanhSachHoaDonDTO getHoaDonByIdTaiKhoan(@PathVariable int id,@PathVariable int n) {
		return hoaDonService.getHoaDonByIdTaiKhoan(id, n*12);
	}
	
	@GetMapping("/hoaDon/tongTien")
	public DoanhThuNgayDTO getTongTienToDay() {
		return hoaDonService.getTongTienByNgay(LocalDate.now());
	}
	
	@GetMapping("/hoaDon/tongTien/homQua")
	public float soSanhHomQua() {
		return hoaDonService.soSanhHomQua();
	}
	
	@GetMapping("/hoaDon/tongTien/thangTruoc")
	public float soSanhThangTruoc() {
		return hoaDonService.soSanhThangTruoc();
	}
	
	@GetMapping("/hoaDon/tongTien/thang")
	public DanhSachTongTienThangDTO getTongTienThang() {
		return hoaDonService.getTongTienThang();
	}
	@GetMapping("/chiTietHoaDon/tongTien")
	public DanhSachChiTietSanPhamTheoDoanhThuDTO getDanhSachChiTietSanPhamTheoDoanhThu() {
		return chiTietHoaDonService.getDanhSachChiTietSanPhamTheoDoanhThu();
	}
	
	@GetMapping("/chiTietHoaDon/soLuong")
	public DanhSachChiTietSanPhamTheoDoanhThuDTO getDanhSachChiTietSanPhamTheoSoLuong() {
		return chiTietHoaDonService.getDanhSachChiTietSanPhamTheoSoLuong();
	}
}
