package com.nk.hinhanhService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nk.hinhanhService.DTO.DanhSachHinhAnhDTO;
import com.nk.hinhanhService.DTO.HinhAnhDTO;
import com.nk.hinhanhService.dao.HinhAnhDAO;
import com.nk.hinhanhService.entity.HinhAnh;

@Service
public class HinhAnhService {

	
	@Autowired
	private HinhAnhDAO hinhAnhDAO;
	
	public DanhSachHinhAnhDTO getHinhAnhByIdSanPham(int idSanPham){
		DanhSachHinhAnhDTO danhSachHinhAnhDTO = new DanhSachHinhAnhDTO();
		List<HinhAnh> hinhAnhs = hinhAnhDAO.getHinhAnhByIdSanPham(idSanPham);
		List<HinhAnhDTO> hinhAnhDTOs = new ArrayList<HinhAnhDTO>();
		for (HinhAnh hinhAnh : hinhAnhs) {
			HinhAnhDTO hinhAnhDTO = chuyenHinhAnhSangHinhAnhDTO(hinhAnh);
			hinhAnhDTOs.add(hinhAnhDTO);
		}
		danhSachHinhAnhDTO.setHinhAnhDTOs(hinhAnhDTOs);
		return danhSachHinhAnhDTO;
	}
	
	public HinhAnhDTO getHinhAnhByIdSanPhamAndIdMau(int idSanPham,int idMau) {
		HinhAnh hinhAnh = hinhAnhDAO.getHinhAnhByIdSanPhamAndIdMau(idSanPham, idMau);
		return chuyenHinhAnhSangHinhAnhDTO(hinhAnh);
	}
	
	public boolean themHinhAnh(HinhAnhDTO hinhAnhDTO) {
		HinhAnh hinhAnh = new HinhAnh();
		hinhAnh.setIdMau(hinhAnhDTO.getIdMau());
		hinhAnh.setIdSanPham(hinhAnhDTO.getIdSanPham());
		hinhAnh.setUrl(hinhAnhDTO.getUrl());
		try {
			hinhAnhDAO.save(hinhAnh);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean suaHinhAnh(HinhAnhDTO hinhAnhDTO) {
		System.out.println(hinhAnhDTO.toString());
			HinhAnh hinhAnh = hinhAnhDAO.getHinhAnhByIdSanPhamAndIdMau(hinhAnhDTO.getIdSanPham(), hinhAnhDTO.getIdMau());
			if(hinhAnh == null) {
				hinhAnh = new HinhAnh();
				hinhAnh.setIdMau(hinhAnhDTO.getIdMau());
				hinhAnh.setIdSanPham(hinhAnhDTO.getIdSanPham());
			}
			System.out.println(hinhAnh.getIdMau());
			hinhAnh.setUrl(hinhAnhDTO.getUrl());
		
		try {
			hinhAnhDAO.save(hinhAnh);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	public void xoaHinhAnh(int idSanPham) {
		hinhAnhDAO.xoaHinhAnh(idSanPham);
	}
	
	
	private HinhAnhDTO chuyenHinhAnhSangHinhAnhDTO(HinhAnh hinhAnh) {
		HinhAnhDTO hinhAnhDTO = new  HinhAnhDTO();
		hinhAnhDTO.setId(hinhAnh.getId());
		hinhAnhDTO.setIdMau(hinhAnh.getIdMau());
		hinhAnhDTO.setIdSanPham(hinhAnh.getIdSanPham());
		hinhAnhDTO.setUrl(hinhAnh.getUrl());
		return hinhAnhDTO;
	}
	
	private HinhAnh chuyenHinhAnhDTOSangHinhAnh(HinhAnhDTO hinhAnhDTO) {
		HinhAnh hinhAnh = new HinhAnh();
		hinhAnh.setIdMau(hinhAnhDTO.getIdMau());
		hinhAnh.setIdSanPham(hinhAnhDTO.getIdSanPham());
		hinhAnh.setUrl(hinhAnhDTO.getUrl());
		return hinhAnh;
	}
}
