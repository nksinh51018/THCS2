package com.nk.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nk.dto.DanhSachLoaiSanPhamDTO;
import com.nk.dto.DanhSachSanPhamDTO;
import com.nk.dto.DanhSachTimKiemDTO;
import com.nk.dto.GioHangDTO;
import com.nk.dto.TimKiemDTO;
import com.nk.service.LoaiSanPhamService;
import com.nk.service.SanPhamSerivce;
import com.nk.service.TaiKhoanService;
@Controller
@SessionAttributes("GioHang")
public class DanhSachSanPhamLoaiController {
	@Autowired
	private LoaiSanPhamService  loaiSanPhamService;
	
	@Autowired
	private SanPhamSerivce sanPhamService;
	
	@Autowired
	private TaiKhoanService  taiKhoanService;
	@GetMapping("/loaiSanPham/{tenLoai}")
	public String xemDanhSachSanPhamLoai(ModelMap map,@PathVariable String tenLoai,HttpSession httpSession,@CookieValue(value = "tokenJWT", defaultValue = "") String token) {
		String ten ="";
		if(token != "") {
			ten = taiKhoanService.DangNhap(token);
		}
		if(!ten.equals("")) {
			map.addAttribute("ten",ten.charAt(0));
		}
		List<GioHangDTO> gioHangDTOs = (List<GioHangDTO>) httpSession.getAttribute("GioHang");
		map.addAttribute("giohang", gioHangDTOs);
		DanhSachLoaiSanPhamDTO danhSachLoaiSanPhamDTO = loaiSanPhamService.getLoaiSanPham();
		map.addAttribute("danhSachLoaiSanPhamDTO", danhSachLoaiSanPhamDTO);
		TimKiemDTO timKiemDTO = new TimKiemDTO();
		timKiemDTO.setIdMau(0);
		timKiemDTO.setMaxGia(0);
		timKiemDTO.setSize(null);
		timKiemDTO.setMinGia(0);
		DanhSachTimKiemDTO danhSachTimKiemDTOs = sanPhamService.getAllSanPhamLoai(0, tenLoai,timKiemDTO);
		map.addAttribute("danhSachTimKiemDTOs",danhSachTimKiemDTOs);
		map.addAttribute("tenLoai",tenLoai);
		return "DanhSachSanPhamLoaiView";
	}
}
