package com.nk.khuyenmaiService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nk.khuyenmaiService.dao.ChiTietKhuyenMaiDAO;
import com.nk.khuyenmaiService.dto.DanhSachIdSanPham;
import com.nk.khuyenmaiService.entity.ChiTietKhuyenMai;

@Service
public class ChiTietKhuyenMaiService {

	@Autowired
	private ChiTietKhuyenMaiDAO chiTietKhuyenMaiDAO;
	
	public DanhSachIdSanPham getListIdSanPhamByIdKhuyenMai(int idKhuyenMai) {
		DanhSachIdSanPham danhSachIdSanPham = new DanhSachIdSanPham();
		List<Integer> idSanPhams = chiTietKhuyenMaiDAO.getListIdSanPhamByIdKhuyenMai(idKhuyenMai);
		
		danhSachIdSanPham.setIdSanPhams(idSanPhams);
		return danhSachIdSanPham;
	}
	
	public ChiTietKhuyenMai getChiTietByIdKhuyenMaiAndIdSanPham(int idKhuyenMai,int idSanPham) {
		return chiTietKhuyenMaiDAO.getChiTietByIdKhuyenMaiAndIdSanPham(idKhuyenMai, idSanPham);
	}
	
}
