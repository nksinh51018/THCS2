package com.nk.sanphamService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nk.sanphamService.DTO.ChiTietSanPhamDTO;
import com.nk.sanphamService.DTO.DanhSachLoaiSanPhamDTO;
import com.nk.sanphamService.DTO.DanhSachMauDTO;
import com.nk.sanphamService.DTO.DanhSachSanPhamDTO;
import com.nk.sanphamService.DTO.DanhSachTimKiemDTO;
import com.nk.sanphamService.DTO.SanPhamDTO;
import com.nk.sanphamService.DTO.TimKiemDTO;
import com.nk.sanphamService.service.ChiTietSanPhamService;
import com.nk.sanphamService.service.LoaiSanPhamService;
import com.nk.sanphamService.service.MauService;
import com.nk.sanphamService.service.SanPhamService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private LoaiSanPhamService loaiSanPhamService;
	
	@Autowired
	private SanPhamService sanPhamService;
	
	@Autowired
	private ChiTietSanPhamService chiTietSanPhamService;
	
	@Autowired
	private MauService mauService;
	
	@GetMapping("/loaiSanPham")
	public DanhSachLoaiSanPhamDTO getAllLoaiSanPham(){
		return loaiSanPhamService.getAllLoaiSanPham();
	}
	
	@GetMapping("/sanPham/{id}")
	public SanPhamDTO getSanPham(@PathVariable int id) {
		return sanPhamService.getSanPhamByID(id);
	}
	
	@GetMapping("/sanPham/hot")
	public DanhSachSanPhamDTO getSanPhamHot() {
		return sanPhamService.getSanPhamHot();
	}
	
	@GetMapping("/sanPham/all/{n}")
	public DanhSachSanPhamDTO getAllSanPhamFrom(@PathVariable int n) {
		int t = n*12;
		return sanPhamService.getAllSanPhamFrom(t);
	}
	
	@GetMapping("/sanPham/loai/{tenLoai}/{n}")
	public DanhSachSanPhamDTO getAllSanPhamLoaiFrom(@PathVariable int n,@PathVariable String tenLoai) {
		int t = n*12;
		return sanPhamService.getAllSanPhamLoaiFrom(t, tenLoai);
	}
	
	@PostMapping(path = "/sanPham/loai/{tenLoai}/timKiem/{n}",
			consumes = {MediaType.ALL_VALUE})
	public DanhSachTimKiemDTO findSanPham(@RequestBody TimKiemDTO timKiemDTO,@PathVariable String tenLoai,@PathVariable int n) {
		int t=n*12;
		return sanPhamService.findSanPhamByLoaiSanPham(t, tenLoai, timKiemDTO);
	}
	
	@PostMapping("/chiTietSanPham/id")
	public int getIdChiTietSanPhamByIdSanPhamAndIdMauAndSize(@RequestBody ChiTietSanPhamDTO chiTietSanPhamDTO) {
		return chiTietSanPhamService.getIdChiTietSanPhamByIdSanPhamAndIdMauAndSize(chiTietSanPhamDTO);
	}
	
	@GetMapping("/sanPham/timKiem/{tenSanPham}/{n}")
	public DanhSachSanPhamDTO findSanPhamByTenSanPham(@PathVariable String tenSanPham,@PathVariable int n) {
		int t= n*12;
		return sanPhamService.findSanPhamByTenSanPham(tenSanPham, t);
	}
	
	@GetMapping("/chiTietSanPham/{id}")
	public ChiTietSanPhamDTO getChiTietSanPhamById(@PathVariable int id) {
		return chiTietSanPhamService.getChiTietSanPhamById(id);
	}
	 
	
	@GetMapping("/mau")
	public DanhSachMauDTO getAllMau() {
		return mauService.findAllMau();
	}
	
	@PostMapping("/themHoaDon")
	public boolean themHoaDon(@RequestBody ChiTietSanPhamDTO chiTietSanPhamDTO) {
		return chiTietSanPhamService.themHoaDon( chiTietSanPhamDTO);
	}
	 
//	@GetMapping("/themSanPham")
//	public boolean themSanPham() {
//		boolean kt = true;
//		for (int i = 1; i <= 30; i++) {
//			SanPham sanPham = new SanPham();
//			sanPham.setChatLieu("Canvas");
//			sanPham.setGiaTien(i + "00000");
//			LoaiSanPham loaiSanPham =new LoaiSanPham();
//			if(i%3==0) {
//				loaiSanPham.setId(1);
//			}
//			else if(i%3==1) {
//				loaiSanPham.setId(2);
//			}
//			else {
//				loaiSanPham.setId(3);
//			}
//			sanPham.setLoaiSanPham(loaiSanPham);
//			sanPham.setLuotXem(0);
//			sanPham.setMoTa("San pham tot");
//			sanPham.setTenSanPham("Giay " + i);
//			List<ChiTietSanPham> chiTietSanPhams = new ArrayList<ChiTietSanPham>();
//			ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
//			Mau mau = new Mau();
//			mau.setId(1);
//			chiTietSanPham.setMau(mau);
//			chiTietSanPham.setSize("40");
//			chiTietSanPham.setSoLuong(10);
//			chiTietSanPham.setSanPham(sanPham);
//			ChiTietSanPham chiTietSanPham2 = new ChiTietSanPham();
//			chiTietSanPham2.setMau(mau);
//			chiTietSanPham2.setSize("41");
//			chiTietSanPham2.setSoLuong(10);
//			chiTietSanPham2.setSanPham(sanPham);
//			ChiTietSanPham chiTietSanPham3 = new ChiTietSanPham();
//			Mau mau2 = new Mau();
//			mau2.setId(2);
//			chiTietSanPham3.setMau(mau2);
//			chiTietSanPham3.setSize("40");
//			chiTietSanPham3.setSoLuong(10);
//			chiTietSanPham3.setSanPham(sanPham);
//			ChiTietSanPham chiTietSanPham4 = new ChiTietSanPham();
//			chiTietSanPham4.setMau(mau2);
//			chiTietSanPham4.setSize("41");
//			chiTietSanPham4.setSoLuong(10);
//			chiTietSanPham4.setSanPham(sanPham);
//			chiTietSanPhams.add(chiTietSanPham);
//			chiTietSanPhams.add(chiTietSanPham2);
//			chiTietSanPhams.add(chiTietSanPham3);
//			chiTietSanPhams.add(chiTietSanPham4);
//			
//			sanPham.setChiTietSanPhams(chiTietSanPhams);
//			sanPhamService.themSanPham(sanPham);
//		}
//		return kt;
//	}
	
}
