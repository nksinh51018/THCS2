package com.nk.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nk.dto.DanhSachLoaiSanPhamDTO;
import com.nk.dto.GioHangDTO;
import com.nk.dto.TaiKhoanDTO;
import com.nk.service.LoaiSanPhamService;
import com.nk.service.TaiKhoanService;

@Controller
@SessionAttributes("GioHang")
public class GioHangController {
	
	@Autowired
	private LoaiSanPhamService  loaiSanPhamService;
	
	@Autowired
	private TaiKhoanService  taiKhoanService;

	@GetMapping("/user/gioHang")
	public String xemGioHang(ModelMap map,HttpSession httpSession,@CookieValue(value = "tokenJWT", defaultValue = "") String token) {
	
		String ten ="";
		if(token != "") {
			ten = taiKhoanService.DangNhap(token);
		}
		if(!ten.equals("")) {
			TaiKhoanDTO taiKhoanDTO = taiKhoanService.getThongTinTaiKhoan(token);
			map.addAttribute("taiKhoanDTO", taiKhoanDTO);
			System.out.println(taiKhoanDTO);
			map.addAttribute("ten",ten.charAt(0));
			List<GioHangDTO> gioHangDTOs = (List<GioHangDTO>) httpSession.getAttribute("GioHang");
			map.addAttribute("giohang", gioHangDTOs);
			
			DanhSachLoaiSanPhamDTO danhSachLoaiSanPhamDTO = loaiSanPhamService.getLoaiSanPham();
			map.addAttribute("danhSachLoaiSanPhamDTO", danhSachLoaiSanPhamDTO);
			
			return "GioHangView";
		}
		else {
			return "redirect:/dangNhap";
		}
	}
	
}
