package com.nk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nk.dto.DanhSachKhuyenMaiDTO;
import com.nk.dto.DanhSachTaiKhoanDTO;
import com.nk.service.KhuyenMaiService;
import com.nk.service.TaiKhoanService;

@Controller
@RequestMapping("/admin")
public class DanhSachKhuyenMaiAdminController {

	@Autowired
	private KhuyenMaiService khuyenMaiService;
	
	@Autowired
	private TaiKhoanService taiKhoanService;

	@GetMapping("/khuyenMai")
	public String getAllKhuyenMai(@CookieValue(value = "tokenJWT", defaultValue = "") String token,ModelMap map) {
		String ten = "";
		if (token != "") {
			ten = taiKhoanService.getTenDangNhapAdmin(token);
		} else {
			return "redirect:/dangNhap";
		}
		if (!ten.equals("")) {
			map.addAttribute("ten", ten.charAt(0));
			DanhSachKhuyenMaiDTO danhSachKhuyenMaiDTO = khuyenMaiService.getAllKhuyenMai(token);
			map.addAttribute("danhSachKhuyenMaiDTO",danhSachKhuyenMaiDTO);
			return "DanhSachKhuyenMaiAdminView";
		} else {
			return "redirect:/dangNhap";
		}
	}

}
