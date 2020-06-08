package com.nk.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nk.dto.DanhSachHoaDonDTO;
import com.nk.dto.DanhSachSanPhamAdmin;
import com.nk.service.HoaDonService;
import com.nk.service.TaiKhoanService;

@Controller
@SessionAttributes("GioHang")
@RequestMapping("/admin")
public class DanhSachHoaDonAdminController {
	
	@Autowired
	private HoaDonService hoaDonService;
	
	@Autowired
	private TaiKhoanService  taiKhoanService;
	
	@GetMapping(path =  {"/hoaDon"})
	public String xemDanhSachHoaDon(ModelMap map,HttpSession httpSession,@CookieValue(value = "tokenJWT", defaultValue = "") String token) {
		String ten ="";
		if(token != "") {
			ten = taiKhoanService.getTenDangNhapAdmin(token);
		}
		else {
			return "redirect:/dangNhap";
		}
		if(!ten.equals("")) {
			map.addAttribute("ten",ten.charAt(0));
			DanhSachHoaDonDTO danhSachHoaDonDTO = hoaDonService.getAllHoaDon(token);
			map.addAttribute("danhSachHoaDonDTO",danhSachHoaDonDTO);
			return "DanhSachHoaDonAdminView";
		}
		else {
			return "redirect:/dangNhap";
		}
	}
	
}
