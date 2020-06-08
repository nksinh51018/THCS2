package com.nk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nk.dto.DanhSachSanPhamAdmin;
import com.nk.dto.KhuyenMaiDTO;
import com.nk.service.KhuyenMaiService;
import com.nk.service.SanPhamSerivce;
import com.nk.service.TaiKhoanService;

@Controller
@RequestMapping("/admin")
public class ChiTietKhuyenMaiAdminController {

	@Autowired
	private KhuyenMaiService khuyenMaiService;
	
	@Autowired
	private TaiKhoanService taiKhoanService;
	
	@Autowired
	private SanPhamSerivce sanPhamService;
	
	@GetMapping("/chiTietKhuyenMai/{id}")
	public String getKhuyenMaiById(@PathVariable int id,@CookieValue(value = "tokenJWT", defaultValue = "") String token,ModelMap map) {
		String ten ="";
		if(token != "") {
			ten = taiKhoanService.getTenDangNhapAdmin(token);
		}
		else {
			return "redirect:/dangNhap";
		}
		if(!ten.equals("")) {
			map.addAttribute("ten",ten.charAt(0));
			KhuyenMaiDTO khuyenMaiDTO = khuyenMaiService.getKhuyenMaiById(token, id);
			DanhSachSanPhamAdmin danhSachSanPhamAdmin = sanPhamService.findSanPham(token);
			map.addAttribute("danhSachSanPhamAdmin",danhSachSanPhamAdmin);
			map.addAttribute("khuyenMaiDTO",khuyenMaiDTO);
			return "ChiTietKhuyenMaiAdminView";
		}
		else {
			return "redirect:/dangNhap";
		}
	}
	
}
