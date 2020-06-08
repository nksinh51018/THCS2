package com.nk.hoadonService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nk.hoadonService.dao.ChiTietHoaDonDAO;
import com.nk.hoadonService.dto.ChiTietSanPhamDTO;
import com.nk.hoadonService.dto.ChiTietSanPhamIn;
import com.nk.hoadonService.dto.ChiTietSanPhamTheoDoanhThuDTO;
import com.nk.hoadonService.dto.DanhSachChiTietSanPhamTheoDoanhThuDTO;

@Service
public class ChiTietHoaDonService {

	@Autowired
	private ChiTietHoaDonDAO chiTietHoaDonDAO;
	
	@Autowired
	private ChiTietSanPhamService chiTietSanPhamService;
	
	public DanhSachChiTietSanPhamTheoDoanhThuDTO getDanhSachChiTietSanPhamTheoDoanhThu() {
	
		List<ChiTietSanPhamIn> chiTietSanPhamIns = chiTietHoaDonDAO.getDanhSachChiTietSanPhamTheoDoanhThu();
		
		List<ChiTietSanPhamTheoDoanhThuDTO> chiTietSanPhamTheoDoanhThuDTOs = new ArrayList<ChiTietSanPhamTheoDoanhThuDTO>();
		for (ChiTietSanPhamIn chiTietSanPhamIn : chiTietSanPhamIns) {
			ChiTietSanPhamTheoDoanhThuDTO chiTietSanPhamTheoDoanhThuDTO = new ChiTietSanPhamTheoDoanhThuDTO();
			ChiTietSanPhamDTO chiTietSanPhamDTO = chiTietSanPhamService.getChiTietSanPhamById(chiTietSanPhamIn.getIdChiTietSanPham());
			chiTietSanPhamTheoDoanhThuDTO.setChiTietSanPhamDTO(chiTietSanPhamDTO);
			chiTietSanPhamTheoDoanhThuDTO.setTongTien(chiTietSanPhamIn.getTongTien());
			chiTietSanPhamTheoDoanhThuDTOs.add(chiTietSanPhamTheoDoanhThuDTO);
			
		}
		DanhSachChiTietSanPhamTheoDoanhThuDTO danhSachChiTietSanPhamTheoDoanhThuDTO = new DanhSachChiTietSanPhamTheoDoanhThuDTO();
		danhSachChiTietSanPhamTheoDoanhThuDTO.setChiTietSanPhamTheoDoanhThuDTOs(chiTietSanPhamTheoDoanhThuDTOs);
		return danhSachChiTietSanPhamTheoDoanhThuDTO;
	}
	
	public DanhSachChiTietSanPhamTheoDoanhThuDTO getDanhSachChiTietSanPhamTheoSoLuong() {
		
		List<ChiTietSanPhamIn> chiTietSanPhamIns = chiTietHoaDonDAO.getDanhSachChiTietSanPhamTheoSoLuong();
		
		List<ChiTietSanPhamTheoDoanhThuDTO> chiTietSanPhamTheoDoanhThuDTOs = new ArrayList<ChiTietSanPhamTheoDoanhThuDTO>();
		for (ChiTietSanPhamIn chiTietSanPhamIn : chiTietSanPhamIns) {
			ChiTietSanPhamTheoDoanhThuDTO chiTietSanPhamTheoDoanhThuDTO = new ChiTietSanPhamTheoDoanhThuDTO();
			ChiTietSanPhamDTO chiTietSanPhamDTO = chiTietSanPhamService.getChiTietSanPhamById(chiTietSanPhamIn.getIdChiTietSanPham());
			chiTietSanPhamTheoDoanhThuDTO.setChiTietSanPhamDTO(chiTietSanPhamDTO);
			chiTietSanPhamTheoDoanhThuDTO.setSoLuong(chiTietSanPhamIn.getSoLuong());
			chiTietSanPhamTheoDoanhThuDTOs.add(chiTietSanPhamTheoDoanhThuDTO);
			
		}
		DanhSachChiTietSanPhamTheoDoanhThuDTO danhSachChiTietSanPhamTheoDoanhThuDTO = new DanhSachChiTietSanPhamTheoDoanhThuDTO();
		danhSachChiTietSanPhamTheoDoanhThuDTO.setChiTietSanPhamTheoDoanhThuDTOs(chiTietSanPhamTheoDoanhThuDTOs);
		return danhSachChiTietSanPhamTheoDoanhThuDTO;
	}
	
	
}