package com.nk.sanphamService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nk.sanphamService.DTO.DanhSachLoaiSanPhamDTO;
import com.nk.sanphamService.DTO.LoaiSanPhamDTO;
import com.nk.sanphamService.dao.LoaiSanPhamDAO;
import com.nk.sanphamService.entity.LoaiSanPham;

@Service
public class LoaiSanPhamService {

	@Autowired
	private LoaiSanPhamDAO loaiSanPhamDAO;
	
	public DanhSachLoaiSanPhamDTO getAllLoaiSanPham() {
		Iterable<LoaiSanPham> iterable =loaiSanPhamDAO.findAll();
		List<LoaiSanPham> loaiSanPhams = new ArrayList<LoaiSanPham>();
		iterable.forEach(loaiSanPhams::add);
		List<LoaiSanPhamDTO> loaiSanPhamDTOs= new ArrayList<LoaiSanPhamDTO>();
		for (LoaiSanPham loaiSanPham : loaiSanPhams) {
			LoaiSanPhamDTO loaiSanPhamDTO = new LoaiSanPhamDTO();
			loaiSanPhamDTO.setId(loaiSanPham.getId());
			loaiSanPhamDTO.setTenLoai(loaiSanPham.getTenLoai());
			loaiSanPhamDTOs.add(loaiSanPhamDTO);
		}
		DanhSachLoaiSanPhamDTO danhSachLoaiSanPhamDTO = new DanhSachLoaiSanPhamDTO();
		danhSachLoaiSanPhamDTO.setLoaiSanPhamDTOs(loaiSanPhamDTOs);
		return danhSachLoaiSanPhamDTO;
	}
	
}
