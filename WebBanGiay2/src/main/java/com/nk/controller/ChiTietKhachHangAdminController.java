package com.nk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nk.dto.DanhSachHoaDonDTO;
import com.nk.dto.TaiKhoanDTO;
import com.nk.service.HoaDonService;
import com.nk.service.TaiKhoanService;

@Controller
@RequestMapping("/admin")
public class ChiTietKhachHangAdminController {

	
	@Autowired
	private TaiKhoanService taiKhoanService;
	
	@Autowired
	private HoaDonService hoaDonService;
	
	@GetMapping("/chiTietKhachHang/{id}")
	public String test(@PathVariable int id,@CookieValue(value = "tokenJWT", defaultValue = "") String token,ModelMap map) {
		String ten ="";
		if(token != "") {
			ten = taiKhoanService.getTenDangNhapAdmin(token);
		}
		else {
			return "redirect:/dangNhap";
		}
		if(!ten.equals("")) {
			map.addAttribute("ten",ten.charAt(0));
			TaiKhoanDTO taiKhoanDTO = taiKhoanService.getTaiKhoanById(token, id);
			map.addAttribute("taiKhoanDTO",taiKhoanDTO);
			DanhSachHoaDonDTO danhSachHoaDonDTO  = hoaDonService.getHoaDonByIdTaiKhoan(token, id);
			map.addAttribute("danhSachHoaDonDTO", danhSachHoaDonDTO);
			return "ChiTietKhachHangAdminView";
		}
		else {
			return "redirect:/dangNhap";
		}
	}
	
}
