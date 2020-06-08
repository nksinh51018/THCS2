package com.nk.khuyenmaiService.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nk.khuyenmaiService.dao.KhuyenMaiDAO;
import com.nk.khuyenmaiService.dto.ChiTietKhuyenMaiDTO;
import com.nk.khuyenmaiService.dto.DanhSachKhuyenMaiDTO;
import com.nk.khuyenmaiService.dto.KhuyenMaiDTO;
import com.nk.khuyenmaiService.entity.ChiTietKhuyenMai;
import com.nk.khuyenmaiService.entity.KhuyenMai;

@Service
public class KhuyenMaiService {

	@Autowired
	private KhuyenMaiDAO khuyenMaiDAO;
	
	@Autowired
	private SanPhamService sanPhamService;
	
	@Autowired
	private ChiTietKhuyenMaiService chiTietKhuyenMaiService;
	
    public int getPhanTramKhuyenMaiByIDSanPham(int id) { 
    	List<KhuyenMai> khuyenMais = khuyenMaiDAO.getKhuyenMaiByIDSanPham(id);
    	int t=0;
    	for (KhuyenMai khuyenMai : khuyenMais) {
			t+=khuyenMai.getPhanTram();
		}
    	return t;
    }
    
    public DanhSachKhuyenMaiDTO getAllKhuyenMai(int n) {
		DanhSachKhuyenMaiDTO danhSachKhuyenMaiDTO = new DanhSachKhuyenMaiDTO();
		
		List<KhuyenMai> khuyenMais = khuyenMaiDAO.getAllKhuyenMai(n);
		List<KhuyenMaiDTO> khuyenMaiDTOs = new ArrayList<KhuyenMaiDTO>();
		for (KhuyenMai khuyenMai : khuyenMais) {
			KhuyenMaiDTO khuyenMaiDTO = chuyenKhuyenMaiSangKhuyenMaiDTO(khuyenMai);
			khuyenMaiDTOs.add(khuyenMaiDTO);
		}
		
		danhSachKhuyenMaiDTO.setKhuyenMaiDTOs(khuyenMaiDTOs);
		
		if(khuyenMais.size() <12) {
			danhSachKhuyenMaiDTO.setCheck(false);
		}
		else {
			danhSachKhuyenMaiDTO.setCheck(true);
		}
		return danhSachKhuyenMaiDTO;
		
	}
    
    public DanhSachKhuyenMaiDTO findKhuyenMai(int a,int n) {
		DanhSachKhuyenMaiDTO danhSachKhuyenMaiDTO = new DanhSachKhuyenMaiDTO();
		LocalDate now = null;
		LocalDate now2 = null;
		if(a==1) {
			now = LocalDate.now();
		}
		if(a == 2) {
			now2 = LocalDate.now();
		}
		List<KhuyenMai> khuyenMais = khuyenMaiDAO.findKhuyenMai(n, now,now2);
		List<KhuyenMaiDTO> khuyenMaiDTOs = new ArrayList<KhuyenMaiDTO>();
		for (KhuyenMai khuyenMai : khuyenMais) {
			KhuyenMaiDTO khuyenMaiDTO = chuyenKhuyenMaiSangKhuyenMaiDTO(khuyenMai);
			khuyenMaiDTOs.add(khuyenMaiDTO);
		}
		
		danhSachKhuyenMaiDTO.setKhuyenMaiDTOs(khuyenMaiDTOs);
		
		if(khuyenMais.size() <12) {
			danhSachKhuyenMaiDTO.setCheck(false);
		}
		else {
			danhSachKhuyenMaiDTO.setCheck(true);
		}
		return danhSachKhuyenMaiDTO;
		
	}
    
    public DanhSachKhuyenMaiDTO findKhuyenMaiByTenKhuyenMai(String tenKhuyenMai,int n) {
		DanhSachKhuyenMaiDTO danhSachKhuyenMaiDTO = new DanhSachKhuyenMaiDTO();
		String s = "%" + tenKhuyenMai +"%";
		List<KhuyenMai> khuyenMais = khuyenMaiDAO.findKhuyenMaiByTenKhuyenMai(n, s);
		List<KhuyenMaiDTO> khuyenMaiDTOs = new ArrayList<KhuyenMaiDTO>();
		for (KhuyenMai khuyenMai : khuyenMais) {
			KhuyenMaiDTO khuyenMaiDTO = chuyenKhuyenMaiSangKhuyenMaiDTO(khuyenMai);
			khuyenMaiDTOs.add(khuyenMaiDTO);
		}
		
		danhSachKhuyenMaiDTO.setKhuyenMaiDTOs(khuyenMaiDTOs);
		
		if(khuyenMais.size() <12) {
			danhSachKhuyenMaiDTO.setCheck(false);
		}
		else {
			danhSachKhuyenMaiDTO.setCheck(true);
		}
		return danhSachKhuyenMaiDTO;
		
	}
    
    public KhuyenMaiDTO getKhuyenMaiById(int id) {
		Optional<KhuyenMai> s = khuyenMaiDAO.findById(id);
		KhuyenMai khuyenMai = s.get();
		KhuyenMaiDTO khuyenMaiDTO = chuyenKhuyenMaiSangKhuyenMaiDTO(khuyenMai);
//		List<ChiTietKhuyenMaiDTO> chiTietKhuyenMaiDTOs = new ArrayList<ChiTietKhuyenMaiDTO>();
//		for (ChiTietKhuyenMai chiTietKhuyenMai : khuyenMai.getChiTietKhuyenMais()) {
//			ChiTietKhuyenMaiDTO chiTietKhuyenMaiDTO = chuyenChiTietKhuyenMaiSangChiTietKhuyenMaiDTO(chiTietKhuyenMai);
//			chiTietKhuyenMaiDTOs.add(chiTietKhuyenMaiDTO);
//		}
//		khuyenMaiDTO.setChiTietKhuyenMaiDTOs(chiTietKhuyenMaiDTOs);
		return khuyenMaiDTO;
	}
    
    public boolean suaKhuyenMai(KhuyenMaiDTO khuyenMaiDTO) {
		Optional<KhuyenMai> s = khuyenMaiDAO.findById(khuyenMaiDTO.getId());
		KhuyenMai khuyenMai  = s.get();
		khuyenMai.setId(khuyenMaiDTO.getId());
		khuyenMai.setNgayBatDau(khuyenMaiDTO.getNgayBatDau());
		khuyenMai.setNgayKetThuc(khuyenMaiDTO.getNgayKetThuc());
		khuyenMai.setPhanTram(khuyenMaiDTO.getPhanTram());
		khuyenMai.setTenKhuyenMai(khuyenMaiDTO.getTenKhuyenMai());
		List<ChiTietKhuyenMai> chiTietKhuyenMais = new ArrayList<ChiTietKhuyenMai>();
		for (ChiTietKhuyenMaiDTO chiTietKhuyenMaiDTO : khuyenMaiDTO.getChiTietKhuyenMaiDTOs()) {
			System.out.println(chiTietKhuyenMaiDTO.getIdSanPham());
			ChiTietKhuyenMai chiTietKhuyenMai = chiTietKhuyenMaiService.getChiTietByIdKhuyenMaiAndIdSanPham(khuyenMai.getId(), chiTietKhuyenMaiDTO.getIdSanPham());
			if(chiTietKhuyenMai == null) {
				chiTietKhuyenMai = new ChiTietKhuyenMai();
				
				chiTietKhuyenMai.setIdSanPham(chiTietKhuyenMaiDTO.getIdSanPham());
				chiTietKhuyenMai.setKhuyenMai(khuyenMai);
			}
			chiTietKhuyenMais.add(chiTietKhuyenMai);
		}
		khuyenMai.setChiTietKhuyenMais(chiTietKhuyenMais);
		try {
			khuyenMaiDAO.save(khuyenMai);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
    
    public boolean themKhuyenMai(KhuyenMaiDTO khuyenMaiDTO) {
		KhuyenMai khuyenMai  = new KhuyenMai();
		khuyenMai.setId(khuyenMaiDTO.getId());
		khuyenMai.setNgayBatDau(khuyenMaiDTO.getNgayBatDau());
		khuyenMai.setNgayKetThuc(khuyenMaiDTO.getNgayKetThuc());
		khuyenMai.setPhanTram(khuyenMaiDTO.getPhanTram());
		khuyenMai.setTenKhuyenMai(khuyenMaiDTO.getTenKhuyenMai());
		List<ChiTietKhuyenMai> chiTietKhuyenMais = new ArrayList<ChiTietKhuyenMai>();
		for (ChiTietKhuyenMaiDTO chiTietKhuyenMaiDTO : khuyenMaiDTO.getChiTietKhuyenMaiDTOs()) {
			System.out.println(chiTietKhuyenMaiDTO.getIdSanPham());
			
				ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
				
				chiTietKhuyenMai.setIdSanPham(chiTietKhuyenMaiDTO.getIdSanPham());
				chiTietKhuyenMai.setKhuyenMai(khuyenMai);
			
			chiTietKhuyenMais.add(chiTietKhuyenMai);
		}
		khuyenMai.setChiTietKhuyenMais(chiTietKhuyenMais);
		try {
			khuyenMaiDAO.save(khuyenMai);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
    
    public boolean xoaKhuyenMai(int id) {
    	Optional<KhuyenMai> s = khuyenMaiDAO.findById(id);
		KhuyenMai khuyenMai  = s.get();
		try {
			khuyenMaiDAO.delete(khuyenMai);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
    }
    
  
    private KhuyenMaiDTO chuyenKhuyenMaiSangKhuyenMaiDTO(KhuyenMai khuyenMai) {
    	KhuyenMaiDTO khuyenMaiDTO = new KhuyenMaiDTO();
    	List<ChiTietKhuyenMai> chiTietKhuyenMais = khuyenMai.getChiTietKhuyenMais();
    	List<ChiTietKhuyenMaiDTO> chiTietKhuyenMaiDTOs = new ArrayList<ChiTietKhuyenMaiDTO>();
    	khuyenMaiDTO.setTenKhuyenMai(khuyenMai.getTenKhuyenMai());
    	khuyenMaiDTO.setChiTietKhuyenMaiDTOs(chiTietKhuyenMaiDTOs);
    	khuyenMaiDTO.setId(khuyenMai.getId());
    	khuyenMaiDTO.setNgayBatDau(khuyenMai.getNgayBatDau());
    	khuyenMaiDTO.setNgayKetThuc(khuyenMai.getNgayKetThuc());
    	khuyenMaiDTO.setPhanTram(khuyenMai.getPhanTram());
    	return khuyenMaiDTO;
    }
    
    
    
	
}
