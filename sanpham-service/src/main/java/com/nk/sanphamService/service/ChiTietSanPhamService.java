package com.nk.sanphamService.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nk.sanphamService.DTO.ChiTietSanPhamDTO;
import com.nk.sanphamService.DTO.DanhSachChiTietSanPhamDTO;
import com.nk.sanphamService.DTO.DanhSachSizeDTO;
import com.nk.sanphamService.DTO.MauDTO;
import com.nk.sanphamService.DTO.TimKiemDTO;
import com.nk.sanphamService.dao.ChiTietSanPhamDAO;
import com.nk.sanphamService.entity.ChiTietSanPham;

@Service
public class ChiTietSanPhamService {

	@Autowired
	private ChiTietSanPhamDAO chiTietSanPhamDAO;
	
	@Autowired
	private HinhAnhService hinhAnhService;
	
	public DanhSachSizeDTO findSizeByLoaiSanPham(TimKiemDTO timKiemDTO,String tenLoai) {
		DanhSachSizeDTO danhSachSizeDTO = new DanhSachSizeDTO();
		List<String> sizes = new ArrayList<String>();
		if(timKiemDTO.getIdMau() == 0 && (timKiemDTO.getMaxGia() == 0 || timKiemDTO.getMinGia() == 0)) {
			sizes = chiTietSanPhamDAO.findSizeByLoaiSanPham(tenLoai);
		}
		else if(timKiemDTO.getIdMau() == 0) {
			sizes = chiTietSanPhamDAO.findSizeByLoaiSanPhamAndGia(tenLoai, timKiemDTO.getMinGia(), timKiemDTO.getMaxGia());
		}
		else if(timKiemDTO.getMaxGia() == 0 || timKiemDTO.getMinGia() == 0) {
			sizes = chiTietSanPhamDAO.findSizeByLoaiSanPhamAndMau(tenLoai, timKiemDTO.getIdMau());
		}
		else {
			sizes = chiTietSanPhamDAO.findSizeByLoaiSanPhamAndGiaAndMau(tenLoai,  timKiemDTO.getMinGia(), timKiemDTO.getMaxGia(),timKiemDTO.getIdMau());
			
		}
		
		danhSachSizeDTO.setSizes(sizes);
		
		return danhSachSizeDTO;
	}
	
	public int getIdChiTietSanPhamByIdSanPhamAndIdMauAndSize(ChiTietSanPhamDTO chiTietSanPhamDTO) {
		return chiTietSanPhamDAO.getIdChiTietSanPhamByIdSanPhamAndIdMauAndSize(chiTietSanPhamDTO.getIdSanPham(), chiTietSanPhamDTO.getMauDTO().getId(), chiTietSanPhamDTO.getSize());
	}
	
	public ChiTietSanPhamDTO getChiTietSanPhamById(int id) {
		ChiTietSanPham chiTietSanPham;
		try {
			Optional<ChiTietSanPham> optional =  chiTietSanPhamDAO.findById(id);
			chiTietSanPham = optional.get();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			chiTietSanPham = new ChiTietSanPham();
		}
		
		return chuyenChiTietSanPhamSangChiTietSanPhamDTO(chiTietSanPham);
	}
	
	public DanhSachSizeDTO getAllSize() {
		DanhSachSizeDTO danhSachSizeDTO = new DanhSachSizeDTO();
		List<String> sizes = chiTietSanPhamDAO.findAllSize();
		danhSachSizeDTO.setSizes(sizes);
		return danhSachSizeDTO;
	}
	
	public int getSoLuongHang() {
		return chiTietSanPhamDAO.getAllSoLuongHang();
	}
	
	public boolean themHoaDon(ChiTietSanPhamDTO chiTietSanPhamDTO) {
		
		try {
			ChiTietSanPham chiTietSanPham = chiTietSanPhamDAO.getChiTietSanPhamByIdSanPhamAndIdMauAndSize(chiTietSanPhamDTO.getIdSanPham(), chiTietSanPhamDTO.getMauDTO().getId(), chiTietSanPhamDTO.getSize());
			chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong()-chiTietSanPhamDTO.getSoLuong());
			chiTietSanPhamDAO.save(chiTietSanPham);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	public ChiTietSanPham getChiTietSanPham(ChiTietSanPhamDTO chiTietSanPhamDTO) {;
		return chiTietSanPhamDAO.getChiTietSanPhamByIdSanPhamAndIdMauAndSize(chiTietSanPhamDTO.getIdSanPham(), chiTietSanPhamDTO.getMauDTO().getId(), chiTietSanPhamDTO.getSize());
	}
	
	public DanhSachChiTietSanPhamDTO getAllChiTiet(int n) {
		List<ChiTietSanPham> chiTietSanPhams = chiTietSanPhamDAO.getAllChiTiet(n);
		
		DanhSachChiTietSanPhamDTO danhSachChiTietSanPhamDTO = new DanhSachChiTietSanPhamDTO();
		List<ChiTietSanPhamDTO> chiTietSanPhamDTOs = new ArrayList<ChiTietSanPhamDTO>();
		for (ChiTietSanPham chiTietSanPham : chiTietSanPhams) {
			if(chiTietSanPham.getSoLuong() >0) {
				ChiTietSanPhamDTO chiTietSanPhamDTO = chuyenChiTietSanPhamSangChiTietSanPhamDTO(chiTietSanPham);
				String url = hinhAnhService.getHinhAnhByIdSanPhamAndIdMau(chiTietSanPhamDTO.getIdSanPham(), chiTietSanPham.getMau().getId());
				System.out.println(url);
				chiTietSanPhamDTO.setUrl(url);
				chiTietSanPhamDTOs.add(chiTietSanPhamDTO);
			}
		}
		
		danhSachChiTietSanPhamDTO.setChiTietSanPhamDTOs(chiTietSanPhamDTOs);
		
		if(chiTietSanPhams.size() <12) {
			danhSachChiTietSanPhamDTO.setCheck(false);
		}
		else {
			danhSachChiTietSanPhamDTO.setCheck(true);
		}
		return danhSachChiTietSanPhamDTO;
	}
	
	public boolean huyHoaDon(ChiTietSanPhamDTO chiTietSanPhamDTO) {
		try {
			System.out.println(chiTietSanPhamDTO.getId());
			Optional<ChiTietSanPham> optional = chiTietSanPhamDAO.findById(chiTietSanPhamDTO.getId());
			ChiTietSanPham chiTietSanPham = optional.get();
			chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() + chiTietSanPhamDTO.getSoLuong());
			chiTietSanPhamDAO.save(chiTietSanPham);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	private ChiTietSanPhamDTO chuyenChiTietSanPhamSangChiTietSanPhamDTO(ChiTietSanPham chiTietSanPham) {
		ChiTietSanPhamDTO chiTietSanPhamDTO = new ChiTietSanPhamDTO();
		chiTietSanPhamDTO.setId(chiTietSanPham.getId());
		chiTietSanPhamDTO.setIdSanPham(chiTietSanPham.getSanPham().getId());
		MauDTO mauDTO = new MauDTO();
		mauDTO.setId(chiTietSanPham.getMau().getId());
		mauDTO.setMaMau(chiTietSanPham.getMau().getMaMau());
		mauDTO.setTenMau(chiTietSanPham.getMau().getTenMau());
		chiTietSanPhamDTO.setMauDTO(mauDTO);
		chiTietSanPhamDTO.setSize(chiTietSanPham.getSize());
		chiTietSanPhamDTO.setSoLuong(chiTietSanPham.getSoLuong());
		chiTietSanPhamDTO.setTenSanPham(chiTietSanPham.getSanPham().getTenSanPham());
		return chiTietSanPhamDTO;
	}
	
}
