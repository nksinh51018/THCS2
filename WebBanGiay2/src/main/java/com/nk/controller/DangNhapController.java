package com.nk.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nk.dto.DanhSachLoaiSanPhamDTO;
import com.nk.dto.GioHangDTO;
import com.nk.service.LoaiSanPhamService;
import com.nk.service.TaiKhoanService;


@Controller
@SessionAttributes("GioHang")
public class DangNhapController {

	@Autowired
	private LoaiSanPhamService  loaiSanPhamService;
	
	@Autowired
	private TaiKhoanService  taiKhoanService;
	
	@GetMapping("/dangNhap")
	public String dangNhap(HttpSession httpSession,ModelMap map,@CookieValue(value = "tokenJWT", defaultValue = "") String token,HttpServletRequest request) {
		if(token != "") {
			String ten = taiKhoanService.DangNhap(token);
			System.out.println(ten);
			if(ten.equals("")) {
				List<GioHangDTO> gioHangDTOs = (List<GioHangDTO>) httpSession.getAttribute("GioHang");
				map.addAttribute("giohang", gioHangDTOs);
				DanhSachLoaiSanPhamDTO danhSachLoaiSanPhamDTO = loaiSanPhamService.getLoaiSanPham();
				map.addAttribute("danhSachLoaiSanPhamDTO", danhSachLoaiSanPhamDTO);
				return "DangNhapView";
			}
			else {
				return "redirect:/";
			}
		}
		else {
			List<GioHangDTO> gioHangDTOs = (List<GioHangDTO>) httpSession.getAttribute("GioHang");
			map.addAttribute("giohang", gioHangDTOs);
			DanhSachLoaiSanPhamDTO danhSachLoaiSanPhamDTO = loaiSanPhamService.getLoaiSanPham();
			map.addAttribute("danhSachLoaiSanPhamDTO", danhSachLoaiSanPhamDTO);
			return "DangNhapView";
		}
	}
	
	@PostMapping("/cookie")
	@ResponseBody
	public boolean setCookie(@RequestBody String token,HttpServletResponse response,HttpServletRequest request) {
		System.out.println(token);
		String[] s =token.split(" ");
		Cookie cookieToken = new Cookie("tokenJWT", s[1]);
		cookieToken.setMaxAge(24*60*60);
		response.addCookie(cookieToken);
		cookieToken.setHttpOnly(false);
		cookieToken.setPath("/WebBanGiay2");
		return true;
	}
	
	@DeleteMapping("/cookie")
	@ResponseBody
	public boolean deleteCookie(HttpServletResponse response,HttpServletRequest request) {
		Cookie cookieToken = new Cookie("tokenJWT", "");
		cookieToken.setMaxAge(0);
		response.addCookie(cookieToken);
		cookieToken.setHttpOnly(false);
		cookieToken.setPath("/WebBanGiay2");
		return true;
	}
	
}
