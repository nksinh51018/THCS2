package com.nk.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nk.dto.DanhSachLoaiSanPhamDTO;
import com.nk.dto.GioHangDTO;
import com.nk.dto.HoaDonDTO;
import com.nk.dto.TaiKhoanDTO;
import com.nk.service.HoaDonService;
import com.nk.service.LoaiSanPhamService;
import com.nk.service.TaiKhoanService;

@Controller
@SessionAttributes("GioHang")
public class ChiTietHoaDonAdminController {

	@Autowired
	private LoaiSanPhamService  loaiSanPhamService;
	
	@Autowired
	private TaiKhoanService  taiKhoanService;
	
	@Autowired
	private HoaDonService  hoaDonService;
	
	@GetMapping("/admin/chiTietHoaDon/{id}")
	public String chiTietHoaDon(@PathVariable int id,ModelMap map,HttpSession httpSession,@CookieValue(value = "tokenJWT", defaultValue = "") String token) {
	
		String ten ="";
		if(token != "") {
			ten = taiKhoanService.getTenDangNhapAdmin(token);
		}
		if(!ten.equals("")) {
			TaiKhoanDTO taiKhoanDTO = taiKhoanService.getThongTinTaiKhoan(token);
			map.addAttribute("taiKhoanDTO", taiKhoanDTO);
			System.out.println(taiKhoanDTO);
			map.addAttribute("ten",ten.charAt(0));
			HoaDonDTO hoaDonDTO =  hoaDonService.getHoaDonAdminById(token, id);
			map.addAttribute("hoaDonDTO", hoaDonDTO);
			return "ChiTietHoaDonAdminView";
		}
		else {
			return "redirect:/dangNhap";
		}
	}
	
	
}
