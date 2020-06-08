package com.nk.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nk.dto.DanhSachChiTietSanPhamDTO;
import com.nk.service.ChiTietSanPhamService;
import com.nk.service.TaiKhoanService;

@Controller
@SessionAttributes("GioHang")
@RequestMapping("/admin")
public class ThemHoaDonController {

	@Autowired
	private TaiKhoanService  taiKhoanService;
	
	@Autowired
	private ChiTietSanPhamService  chiTietSanPhamService;
	
	@GetMapping("/themHoaDon")
	public String themHoaDon(ModelMap map,HttpSession httpSession,@CookieValue(value = "tokenJWT", defaultValue = "") String token) {
		String ten ="";
		if(token != "") {
			ten = taiKhoanService.getTenDangNhapAdmin(token);
		}
		else {
			return "redirect:/dangNhap";
		}
		if(!ten.equals("")) {
			map.addAttribute("ten",ten.charAt(0));
			DanhSachChiTietSanPhamDTO danhSachChiTietSanPhamDTO = chiTietSanPhamService.getAllChiTiet(token);
			map.addAttribute("danhSachChiTietSanPhamDTO",danhSachChiTietSanPhamDTO);
			return "ThemHoaDonAdminView";
		}
		else {
			return "redirect:/dangNhap";
		}
	}
	
	@GetMapping("/themHoaDon/test")
	@ResponseBody
	public DanhSachChiTietSanPhamDTO test(@CookieValue(value = "tokenJWT", defaultValue = "") String token) {
		return chiTietSanPhamService.getAllChiTiet(token);
	}
	
}
