package com.nk.controller;

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
import com.nk.dto.DanhSachMauDTO;
import com.nk.dto.DanhSachSanPhamAdmin;
import com.nk.dto.LoaiSanPhamDTO;
import com.nk.dto.MauDTO;
import com.nk.dto.SanPhamDTO;
import com.nk.service.LoaiSanPhamService;
import com.nk.service.MauService;
import com.nk.service.SanPhamSerivce;
import com.nk.service.TaiKhoanService;

@Controller
@SessionAttributes("GioHang")
@RequestMapping("/admin")
public class ChiTietSanPhamAdminController {

	@Autowired
	private SanPhamSerivce sanPhamService;
	
	@Autowired
	private TaiKhoanService  taiKhoanService;
	
	@Autowired
	private MauService  mauService;
	
	@Autowired
	private LoaiSanPhamService loaiSanPhamService;
	
	@GetMapping("/chiTietSanPham/{id}")
	public String xemDanhSachSanPham(ModelMap map,HttpSession httpSession,@CookieValue(value = "tokenJWT", defaultValue = "") String token,@PathVariable int id) {
		String ten ="";
		if(token != "") {
			ten = taiKhoanService.getTenDangNhapAdmin(token);
		}
		else {
			return "redirect:/dangNhap";
		}
		if(!ten.equals("")) {
			map.addAttribute("ten",ten.charAt(0));
			SanPhamDTO sanPhamDTO = sanPhamService.getSanPhamByIdAdmin(id,token);
			map.addAttribute("sanPhamDTO",sanPhamDTO);
			DanhSachMauDTO danhSachMauDTO = mauService.getAllMau();
			map.addAttribute("danhSachMauDTO",danhSachMauDTO);
			DanhSachLoaiSanPhamDTO danhSachLoaiSanPhamDTO = loaiSanPhamService.getLoaiSanPham();
			map.addAttribute("danhSachLoaiSanPhamDTO",danhSachLoaiSanPhamDTO);
			System.out.println(sanPhamDTO.toString());
			return "ChiTietSanPhamAdminView";
		}
		else {
			return "redirect:/dangNhap";
		}
	}
	
	@GetMapping("/chiTietSanPham/test")
	@ResponseBody
	public DanhSachMauDTO xemDanhSachSanPhamtest(ModelMap map,HttpSession httpSession,@CookieValue(value = "tokenJWT", defaultValue = "") String token) {
		return mauService.getAllMau();
	}
	
}
