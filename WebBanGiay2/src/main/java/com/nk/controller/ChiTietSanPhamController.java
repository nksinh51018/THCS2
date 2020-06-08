package com.nk.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nk.dto.ChiTietSanPhamDTO;
import com.nk.dto.DanhSachLoaiSanPhamDTO;
import com.nk.dto.GioHangDTO;
import com.nk.dto.MauDTO;
import com.nk.dto.SanPhamDTO;
import com.nk.service.LoaiSanPhamService;
import com.nk.service.SanPhamSerivce;
import com.nk.service.TaiKhoanService;

@Controller
@SessionAttributes("GioHang")
public class ChiTietSanPhamController {
	@Autowired
	private LoaiSanPhamService  loaiSanPhamService;
	
	@Autowired
	private SanPhamSerivce sanPhamService;
	
	@Autowired
	private TaiKhoanService  taiKhoanService;
	
	@GetMapping("/chiTiet/{id}")
	public String getSanPhamById(@PathVariable int id,ModelMap map,HttpSession httpSession,@CookieValue(value = "tokenJWT", defaultValue = "") String token) {
		
		String ten ="";
		if(token != "") {
			ten = taiKhoanService.DangNhap(token);
		}
		if(!ten.equals("")) {
			map.addAttribute("ten",ten.charAt(0));
		}
		List<GioHangDTO> gioHangDTOs = (List<GioHangDTO>) httpSession.getAttribute("GioHang");
		map.addAttribute("giohang", gioHangDTOs);
		
		DanhSachLoaiSanPhamDTO danhSachLoaiSanPhamDTO = loaiSanPhamService.getLoaiSanPham();
		map.addAttribute("danhSachLoaiSanPhamDTO", danhSachLoaiSanPhamDTO);
		SanPhamDTO sanPhamDTO = sanPhamService.getSanPhamById(id);
		map.addAttribute("sanPhamDTO",sanPhamDTO);
		
		Set<MauDTO> mauDTOs = new HashSet<MauDTO>();
		
		int[] a = new int[55];
		for(int i=0;i<50;i++) {
			a[i] = 0;
		}
		Set<String> sizes = new HashSet<String>();
		for (ChiTietSanPhamDTO chiTietSanPhamDTO : sanPhamDTO.getChiTietSanPhamDTOs()) {
			MauDTO mauDTO = chiTietSanPhamDTO.getMauDTO();
			if(a[mauDTO.getId()]==0) {
				mauDTOs.add(mauDTO);
				a[mauDTO.getId()]=1;
			}
			sizes.add(chiTietSanPhamDTO.getSize());
		}

		map.addAttribute("mauDTOs",mauDTOs);
		map.addAttribute("sizes", sizes);
		for (MauDTO m : mauDTOs) {
			System.out.println(m.toString());
		}
		for (String string : sizes) {
			System.out.println(string);
		}
		return "ChiTietSanPhamView";
		
	}
	
}
