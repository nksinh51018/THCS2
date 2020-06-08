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

import com.nk.dto.DanhSachSanPhamAdmin;
import com.nk.service.SanPhamSerivce;
import com.nk.service.TaiKhoanService;

@Controller
@SessionAttributes("GioHang")
@RequestMapping("/admin")
public class DanhSachSanPhamAdminController {
	
	@Autowired
	private SanPhamSerivce sanPhamService;
	
	@Autowired
	private TaiKhoanService  taiKhoanService;
	
	@GetMapping(path =  {"/sanPham"})
	public String xemDanhSachSanPham(ModelMap map,HttpSession httpSession,@CookieValue(value = "tokenJWT", defaultValue = "") String token) {
		String ten ="";
		if(token != "") {
			ten = taiKhoanService.getTenDangNhapAdmin(token);
		}
		else {
			return "redirect:/dangNhap";
		}
		if(!ten.equals("")) {
			map.addAttribute("ten",ten.charAt(0));
			DanhSachSanPhamAdmin danhSachSanPhamAdmin = sanPhamService.findSanPham(token);
			map.addAttribute("danhSachSanPhamAdmin",danhSachSanPhamAdmin);
			return "DanhSachSanPhamAdminView";
		}
		else {
			return "redirect:/dangNhap";
		}
	}
	
	@GetMapping("/sanPham/test")
	@ResponseBody
	public String xemDanhSachSanPhamtest(ModelMap map,HttpSession httpSession,@CookieValue(value = "tokenJWT", defaultValue = "") String token) {
		return taiKhoanService.getTenDangNhapAdmin(token);
	}
	
	
}