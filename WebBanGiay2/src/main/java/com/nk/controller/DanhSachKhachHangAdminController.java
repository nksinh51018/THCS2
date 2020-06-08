package com.nk.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nk.dto.DanhSachTaiKhoanDTO;
import com.nk.service.TaiKhoanService;

@Controller
@RequestMapping("/admin")
public class DanhSachKhachHangAdminController {

	@Autowired
	private TaiKhoanService taiKhoanService;
	
	@GetMapping(path =  {"/khachHang"})
	public String xemDanhSachKhacHang(ModelMap map,HttpSession httpSession,@CookieValue(value = "tokenJWT", defaultValue = "") String token) {
		String ten ="";
		if(token != "") {
			ten = taiKhoanService.getTenDangNhapAdmin(token);
		}
		else {
			return "redirect:/dangNhap";
		}
		if(!ten.equals("")) {
			map.addAttribute("ten",ten.charAt(0));
			DanhSachTaiKhoanDTO danhSachTaiKhoanDTO = taiKhoanService.getAllTaiKhoan(token);
			map.addAttribute("danhSachTaiKhoanDTO",danhSachTaiKhoanDTO);
			return "DanhSachKhachHangAdminView";
		}
		else {
			return "redirect:/dangNhap";
		}
	}
	
}
