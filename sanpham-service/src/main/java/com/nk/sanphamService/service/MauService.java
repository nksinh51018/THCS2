package com.nk.sanphamService.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nk.sanphamService.DTO.DanhSachMauDTO;
import com.nk.sanphamService.DTO.MauDTO;
import com.nk.sanphamService.DTO.TimKiemDTO;
import com.nk.sanphamService.dao.MauDAO;
import com.nk.sanphamService.entity.Mau;

@Service
public class MauService {

	@Autowired
	private MauDAO mauDAO;
	
	
	public DanhSachMauDTO findMauByLoaiSanPham(String tenLoai,TimKiemDTO timKiemDTO) {
		DanhSachMauDTO danhSachMauDTO = new DanhSachMauDTO();
		List<Mau> maus = new ArrayList<Mau>();
		if(timKiemDTO.getSize() == null && (timKiemDTO.getMinGia() == 0 || timKiemDTO.getMaxGia() < 0)) {
			maus = mauDAO.findMauByLoaiSanPham(tenLoai);
		}
		else if(timKiemDTO.getMinGia() == 0 || timKiemDTO.getMaxGia() == 0) {
			maus = mauDAO.findMauByLoaiSanPhamAndSize(tenLoai, timKiemDTO.getSize());
		}
		else if(timKiemDTO.getSize() == null) {
			maus = mauDAO.findMauByLoaiSanPhamAndGiaTien(tenLoai, timKiemDTO.getMinGia(), timKiemDTO.getMaxGia());
		}
		else {
			maus = mauDAO.findMauByLoaiSanPhamAndGiaTienAndSize(tenLoai, timKiemDTO.getMinGia(), timKiemDTO.getMaxGia(), timKiemDTO.getSize());
		}
		List<MauDTO> mauDTOs = new ArrayList<MauDTO>();
		for (Mau mau : maus) {
			MauDTO mauDTO = chuyenMauSangMauDTO(mau);
			mauDTOs.add(mauDTO);
		}
		danhSachMauDTO.setMauDTOs(mauDTOs);
		return danhSachMauDTO;
	}
	
	public DanhSachMauDTO findAllMau() {
		DanhSachMauDTO danhSachMauDTO = new DanhSachMauDTO();
		Iterable<Mau> iterable =mauDAO.findAll();
		List<Mau> maus = new ArrayList<Mau>();
		iterable.forEach(maus::add);
		List<MauDTO> mauDTOs = new ArrayList<MauDTO>();
		for (Mau mau : maus) {
			MauDTO mauDTO = chuyenMauSangMauDTO(mau);
			mauDTOs.add(mauDTO);
		}
		danhSachMauDTO.setMauDTOs(mauDTOs);
		return danhSachMauDTO;
	}
	
	public Mau getMauById(int id) {
		Mau mau = mauDAO.findMauById(id);
		if(mau == null) {
			return null;
		}
		else {
			return mau;
		}
	}
	
	public Mau getMauByTenMau(String tenMau) {
		
		return mauDAO.findMauByTenMau(tenMau);
	}
	
	public Mau themMau(Mau mau) {
		try {
			return mauDAO.save(mau);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Mau();
		}
	}
	
	private MauDTO chuyenMauSangMauDTO(Mau mau) {
		MauDTO mauDTO = new MauDTO();
		mauDTO.setId(mau.getId());
		mauDTO.setMaMau(mau.getMaMau());
		mauDTO.setTenMau(mau.getTenMau());
		return mauDTO;
	}
	
}
