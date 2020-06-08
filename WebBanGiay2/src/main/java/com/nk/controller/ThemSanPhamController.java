package com.nk.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nk.dto.DanhSachLoaiSanPhamDTO;
import com.nk.dto.DanhSachMauDTO;
import com.nk.service.LoaiSanPhamService;
import com.nk.service.MauService;
import com.nk.service.TaiKhoanService;

@Controller
@SessionAttributes("GioHang")
@RequestMapping("/admin")
public class ThemSanPhamController {

	
	@Autowired
	private TaiKhoanService  taiKhoanService;
	
	@Autowired
	private MauService  mauService;
	
	@Autowired
	private LoaiSanPhamService loaiSanPhamService;
	
	@GetMapping("/themSanPham")
	public String themSanPham(ModelMap map,HttpSession httpSession,@CookieValue(value = "tokenJWT", defaultValue = "") String token) {
		String ten ="";
		if(token != "") {
			ten = taiKhoanService.getTenDangNhapAdmin(token);
		}
		else {
			return "redirect:/dangNhap";
		}
		if(!ten.equals("")) {
			map.addAttribute("ten",ten.charAt(0));
			DanhSachMauDTO danhSachMauDTO = mauService.getAllMau();
			map.addAttribute("danhSachMauDTO",danhSachMauDTO);
			DanhSachLoaiSanPhamDTO danhSachLoaiSanPhamDTO = loaiSanPhamService.getLoaiSanPham();
			map.addAttribute("danhSachLoaiSanPhamDTO",danhSachLoaiSanPhamDTO);
			return "ThemSanPhamAdminView";
		}
		else {
			return "redirect:/dangNhap";
		}
	}
}
