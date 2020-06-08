package com.nk.sanphamService.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nk.sanphamService.DTO.ChiTietSanPhamDTO;
import com.nk.sanphamService.DTO.DanhSachHinhAnhDTO;
import com.nk.sanphamService.DTO.DanhSachMauDTO;
import com.nk.sanphamService.DTO.DanhSachSanPhamAdmin;
import com.nk.sanphamService.DTO.DanhSachSanPhamDTO;
import com.nk.sanphamService.DTO.DanhSachSizeDTO;
import com.nk.sanphamService.DTO.DanhSachTimKiemDTO;
import com.nk.sanphamService.DTO.GiaDTO;
import com.nk.sanphamService.DTO.LoaiSanPhamDTO;
import com.nk.sanphamService.DTO.MauDTO;
import com.nk.sanphamService.DTO.SanPhamDTO;
import com.nk.sanphamService.DTO.TimKiemDTO;
import com.nk.sanphamService.dao.SanPhamDAO;
import com.nk.sanphamService.entity.ChiTietSanPham;
import com.nk.sanphamService.entity.LoaiSanPham;
import com.nk.sanphamService.entity.Mau;
import com.nk.sanphamService.entity.SanPham;

@Service
public class SanPhamService {

	@Autowired
	private SanPhamDAO sanPhamDAO;
	
	@Autowired
	private KhuyenMaiService khuyenMaiService;
	
	@Autowired
	private HinhAnhService hinhAnhService;
	
	@Autowired
	private MauService mauService;
	
	@Autowired
	private ChiTietSanPhamService chiTietSanPhamService;

	
	public int themSanPham(SanPhamDTO sanPhamDTO) {
		SanPham sanPham = new SanPham();
		sanPham.setChatLieu(sanPhamDTO.getChatLieu());
		List<ChiTietSanPham> chiTietSanPhams = new ArrayList<ChiTietSanPham>();
		List<ChiTietSanPhamDTO> chiTietSanPhamDTOs = sanPhamDTO.getChiTietSanPhamDTOs();
		//System.out.println(chiTietSanPhamDTOs.size());
		for (ChiTietSanPhamDTO chiTietSanPhamDTO : chiTietSanPhamDTOs) {
			//chiTietSanPhamDTO.setIdSanPham(sanPhamDTO.getId());
			ChiTietSanPham chiTietSanPham =  new ChiTietSanPham();
				Mau mau = mauService.getMauById((chiTietSanPhamDTO.getMauDTO().getId()));
				if(mau == null) {
					mau = new Mau();
					mau.setId(0);
					mau.setMaMau(chiTietSanPhamDTO.getMauDTO().getMaMau());
					mau.setTenMau(chiTietSanPhamDTO.getMauDTO().getTenMau());
					System.out.println("Adsad");
					mau =  mauService.themMau(mau);
					System.out.println(mau.getTenMau());
				}
				else {
					System.out.println(mau.getTenMau());
				}
				chiTietSanPham.setMau(mau);
				chiTietSanPham.setSanPham(sanPham);
				chiTietSanPham.setSize(chiTietSanPhamDTO.getSize());
			
			chiTietSanPham.setSoLuong(chiTietSanPhamDTO.getSoLuong());
			chiTietSanPhams.add(chiTietSanPham);
		}
		sanPham.setChiTietSanPhams(chiTietSanPhams);
		sanPham.setGiaTien(sanPhamDTO.getGiaTien());
		LoaiSanPham loaiSanPham = new LoaiSanPham();
		loaiSanPham.setId(sanPhamDTO.getLoaiSanPhamDTO().getId());
		sanPham.setLoaiSanPham(loaiSanPham);
		sanPham.setLuotXem(sanPhamDTO.getLuotXem());
		sanPham.setMoTa(sanPhamDTO.getMoTa());
		sanPham.setTenSanPham(sanPhamDTO.getTenSanPham());
		try {
			//System.out.println(sanPham.toString());
			SanPham s= sanPhamDAO.save(sanPham);
			return s.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public boolean suaSanPham(SanPhamDTO sanPhamDTO) {
		Optional<SanPham> optional = sanPhamDAO.findById(sanPhamDTO.getId());
		SanPham sanPham = optional.get();
		sanPham.setChatLieu(sanPhamDTO.getChatLieu());
		List<ChiTietSanPham> chiTietSanPhams = new ArrayList<ChiTietSanPham>();
		List<ChiTietSanPhamDTO> chiTietSanPhamDTOs = sanPhamDTO.getChiTietSanPhamDTOs();
		System.out.println(chiTietSanPhamDTOs.size());
		for (ChiTietSanPhamDTO chiTietSanPhamDTO : chiTietSanPhamDTOs) {
			chiTietSanPhamDTO.setIdSanPham(sanPhamDTO.getId());
			ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getChiTietSanPham(chiTietSanPhamDTO);
			if(chiTietSanPham == null) {
				chiTietSanPham = new ChiTietSanPham();
				Mau mau = mauService.getMauById((chiTietSanPhamDTO.getMauDTO().getId()));
				if(mau == null) {
					mau = new Mau();
					mau.setId(0);
					mau.setMaMau(chiTietSanPhamDTO.getMauDTO().getMaMau());
					mau.setTenMau(chiTietSanPhamDTO.getMauDTO().getTenMau());
					System.out.println("Adsad");
					mau =  mauService.themMau(mau);
					System.out.println(mau.getTenMau());
				}
				else {
					System.out.println(mau.getTenMau());
				}
				chiTietSanPham.setMau(mau);
				chiTietSanPham.setSanPham(sanPham);
				chiTietSanPham.setSize(chiTietSanPhamDTO.getSize());
			}
			chiTietSanPham.setSoLuong(chiTietSanPhamDTO.getSoLuong());
			chiTietSanPhams.add(chiTietSanPham);
		}
		sanPham.setChiTietSanPhams(chiTietSanPhams);
		sanPham.setGiaTien(sanPhamDTO.getGiaTien());
		LoaiSanPham loaiSanPham = new LoaiSanPham();
		loaiSanPham.setId(sanPhamDTO.getLoaiSanPhamDTO().getId());
		sanPham.setLoaiSanPham(loaiSanPham);
		sanPham.setLuotXem(sanPhamDTO.getLuotXem());
		sanPham.setMoTa(sanPhamDTO.getMoTa());
		sanPham.setTenSanPham(sanPhamDTO.getTenSanPham());
		try {
			//System.out.println(sanPham.toString());
			sanPhamDAO.save(sanPham);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public DanhSachSanPhamDTO getSanPhamHot(){
		List<SanPham> sanPhams = sanPhamDAO.getListSanPhamHot();
		DanhSachSanPhamDTO danhSachSanPhamDTO = new DanhSachSanPhamDTO();
		List<SanPhamDTO> sanPhamDTOs = new ArrayList<SanPhamDTO>();
		for (SanPham sanPham : sanPhams) {
			SanPhamDTO sanPhamDTO = chuyenDoiSanPhamSangSanPhamDTO2(sanPham);
			sanPhamDTOs.add(sanPhamDTO);
		}
		danhSachSanPhamDTO.setSanPhamDTOs(sanPhamDTOs);
		return danhSachSanPhamDTO;
	} 
	
	
	public DanhSachSanPhamDTO getAllSanPhamFrom(int n) {
		List<SanPham> sanPhams = sanPhamDAO.getListSanPhamFrom(n);
		DanhSachSanPhamDTO danhSachSanPhamDTO = new DanhSachSanPhamDTO();
		List<SanPhamDTO> sanPhamDTOs = new ArrayList<SanPhamDTO>();
		for (SanPham sanPham : sanPhams) {
			SanPhamDTO sanPhamDTO = chuyenDoiSanPhamSangSanPhamDTO2(sanPham);
			sanPhamDTOs.add(sanPhamDTO);
		}
		if(sanPhams.size() < 12) {
			danhSachSanPhamDTO.setCheck(false);
		}
		danhSachSanPhamDTO.setSanPhamDTOs(sanPhamDTOs);
		return danhSachSanPhamDTO;
	}
	
	public DanhSachSanPhamDTO getAllSanPhamLoaiFrom(int n,String tenLoai) {
		List<SanPham> sanPhams = sanPhamDAO.getListSanPhamLoaiFrom(n, tenLoai);
		DanhSachSanPhamDTO danhSachSanPhamDTO = new DanhSachSanPhamDTO();
		List<SanPhamDTO> sanPhamDTOs = new ArrayList<SanPhamDTO>();
		for (SanPham sanPham : sanPhams) {
			SanPhamDTO sanPhamDTO = chuyenDoiSanPhamSangSanPhamDTO2(sanPham);
			sanPhamDTOs.add(sanPhamDTO);
		}
		if(sanPhams.size() < 12) {
			danhSachSanPhamDTO.setCheck(false);
		}
		danhSachSanPhamDTO.setSanPhamDTOs(sanPhamDTOs);
		return danhSachSanPhamDTO;
	}
	
	public DanhSachTimKiemDTO findSanPhamByLoaiSanPham(int n,String tenLoai,TimKiemDTO timKiemDTO) {
		DanhSachSanPhamDTO danhSachSanPhamDTO = new DanhSachSanPhamDTO();
		List<SanPhamDTO> sanPhamDTOs = new ArrayList<SanPhamDTO>();
		List<SanPham> sanPhams = new ArrayList<SanPham>();
		
		if(timKiemDTO.getIdMau() == 0 && timKiemDTO.getSize() == null && (timKiemDTO.getMaxGia() == 0 || timKiemDTO.getMinGia() == 0)) {
			sanPhams = sanPhamDAO.findSanPhamByLoaiSanPham(tenLoai,n);
		}
		else if(timKiemDTO.getIdMau() != 0 && timKiemDTO.getSize() == null && (timKiemDTO.getMaxGia() == 0 || timKiemDTO.getMinGia() == 0)) {
			sanPhams = sanPhamDAO.findSanPhamByLoaiSanPhamAndMau(tenLoai, timKiemDTO.getIdMau(),n);
		}
		else if(timKiemDTO.getIdMau() == 0 && timKiemDTO.getSize() != null && (timKiemDTO.getMaxGia() == 0 || timKiemDTO.getMinGia() == 0)) {
			sanPhams = sanPhamDAO.findSanPhamByLoaiSanPhamAndSize(tenLoai, timKiemDTO.getSize(),n);
		}
		else if(timKiemDTO.getIdMau() == 0 && timKiemDTO.getSize() == null && (timKiemDTO.getMaxGia() != 0 && timKiemDTO.getMinGia() != 0)) {
			sanPhams = sanPhamDAO.findSanPhamByLoaiSanPhamAndGia(tenLoai, timKiemDTO.getMinGia(), timKiemDTO.getMaxGia(),n);
		}
		else if(timKiemDTO.getIdMau() != 0 && timKiemDTO.getSize() != null && (timKiemDTO.getMaxGia() == 0 && timKiemDTO.getMinGia() == 0)) {
			sanPhams = sanPhamDAO.findSanPhamByLoaiSanPhamAndMauAndSize(tenLoai, timKiemDTO.getIdMau(), timKiemDTO.getSize(),n);
		}
		else if(timKiemDTO.getIdMau() != 0 && timKiemDTO.getSize() == null && (timKiemDTO.getMaxGia() != 0 && timKiemDTO.getMinGia() != 0)) {
			sanPhams = sanPhamDAO.findSanPhamByLoaiSanPhamAndMauAndGia(tenLoai, timKiemDTO.getIdMau(), timKiemDTO.getMinGia(), timKiemDTO.getMaxGia(),n);
		}
		else if(timKiemDTO.getIdMau() == 0 && timKiemDTO.getSize() != null && (timKiemDTO.getMaxGia() != 0 && timKiemDTO.getMinGia() != 0)) {
			sanPhams = sanPhamDAO.findSanPhamByLoaiSanPhamAndSizeAndGia(tenLoai, timKiemDTO.getSize(), timKiemDTO.getMinGia(), timKiemDTO.getMaxGia(),n);
		}
		else {
			sanPhams = sanPhamDAO.findSanPhamByLoaiSanPhamAndSizeAndGiaAndMau(tenLoai, timKiemDTO.getSize(), timKiemDTO.getMinGia(), timKiemDTO.getMaxGia(), timKiemDTO.getIdMau(),n);
		}
		for (SanPham sanPham : sanPhams) {
			SanPhamDTO sanPhamDTO = chuyenDoiSanPhamSangSanPhamDTO2(sanPham);
			sanPhamDTOs.add(sanPhamDTO);
		}
		
		danhSachSanPhamDTO.setSanPhamDTOs(sanPhamDTOs);
		System.out.println(sanPhams.size());
		if(sanPhams.size() <12) {
			danhSachSanPhamDTO.setCheck(false);
		}
		else {
			danhSachSanPhamDTO.setCheck(true);
		}
		DanhSachTimKiemDTO danhSachTimKiemDTO = new DanhSachTimKiemDTO();
		DanhSachMauDTO danhSachMauDTO = new DanhSachMauDTO();
		danhSachMauDTO = mauService.findMauByLoaiSanPham(tenLoai, timKiemDTO);
		danhSachTimKiemDTO.setDanhSachMauDTO(danhSachMauDTO);
		DanhSachSizeDTO danhSachSizeDTO = new DanhSachSizeDTO();
		danhSachSizeDTO = chiTietSanPhamService.findSizeByLoaiSanPham(timKiemDTO, tenLoai);
		danhSachTimKiemDTO.setDanhSachSizeDTO(danhSachSizeDTO);
		GiaDTO giaDTO = new GiaDTO();
		giaDTO = findGiaByLoaiSanPham(timKiemDTO, tenLoai);
		danhSachTimKiemDTO.setGiaDTO(giaDTO);
		danhSachTimKiemDTO.setDanhSachSanPhamDTO(danhSachSanPhamDTO);;
		return danhSachTimKiemDTO;
	}
	
	public SanPhamDTO getSanPhamByIDAdmin(int id) {
		
		SanPham sanPham = sanPhamDAO.findById(id).get();
		//sanPham.setLuotXem(sanPham.getLuotXem() + 1);
		sanPhamDAO.save(sanPham);
		return chuyenDoiSanPhamSangSanPhamDTO(sanPham);
		
	}
	
public SanPhamDTO getSanPhamByID(int id) {
		
		SanPham sanPham = sanPhamDAO.findById(id).get();
		sanPham.setLuotXem(sanPham.getLuotXem() + 1);
		sanPhamDAO.save(sanPham);
		SanPhamDTO sanPhamDTO = new SanPhamDTO();
		LoaiSanPhamDTO loaiSanPhamDTO =new LoaiSanPhamDTO();
		loaiSanPhamDTO.setId(sanPham.getLoaiSanPham().getId());
		loaiSanPhamDTO.setTenLoai(sanPham.getLoaiSanPham().getTenLoai());
		sanPhamDTO.setChatLieu(sanPham.getChatLieu());
		sanPhamDTO.setGiaTien(sanPham.getGiaTien());
		sanPhamDTO.setId(sanPham.getId());
		sanPhamDTO.setLoaiSanPhamDTO(loaiSanPhamDTO);
		sanPhamDTO.setLuotXem(sanPham.getLuotXem());
		sanPhamDTO.setMoTa(sanPham.getMoTa());
		sanPhamDTO.setTenSanPham(sanPham.getTenSanPham());
		List<ChiTietSanPhamDTO> chiTietSanPhamDTOs= new ArrayList<ChiTietSanPhamDTO>();
		List<ChiTietSanPham> chiTietSanPhams = sanPham.getChiTietSanPhams();
		for (ChiTietSanPham chiTietSanPham : chiTietSanPhams) {
			if(chiTietSanPham.getSoLuong() > 0) {
				ChiTietSanPhamDTO chiTietSanPhamDTO = new ChiTietSanPhamDTO();
				chiTietSanPhamDTO.setId(chiTietSanPham.getId());
				chiTietSanPhamDTO.setIdSanPham(sanPham.getId());
				MauDTO mauDTO = new MauDTO();
				Mau mau = chiTietSanPham.getMau();
				mauDTO.setId(mau.getId());
				mauDTO.setMaMau(mau.getMaMau());
				mauDTO.setTenMau(mau.getTenMau());
				chiTietSanPhamDTO.setMauDTO(mauDTO);
				chiTietSanPhamDTO.setSoLuong(chiTietSanPham.getSoLuong());
				chiTietSanPhamDTO.setSize(chiTietSanPham.getSize());
				chiTietSanPhamDTOs.add(chiTietSanPhamDTO);
			}
		}
		int km = khuyenMaiService.getKhuyenMaiByIdSanPham(sanPham.getId());
		sanPhamDTO.setKhuyenMai(km);
		sanPhamDTO.setChiTietSanPhamDTOs(chiTietSanPhamDTOs);
		DanhSachHinhAnhDTO danhSachHinhAnhDTO = hinhAnhService.getHinhAnhByIdSanPham(sanPham.getId());
		sanPhamDTO.setHinhAnhDTOs(danhSachHinhAnhDTO.getHinhAnhDTOs());
		return sanPhamDTO;
		
	}
	
	public GiaDTO findGiaByLoaiSanPham(TimKiemDTO timKiemDTO,String tenLoai) {
		GiaDTO giaDTO = new GiaDTO();
		int minGia= 0;
		int maxGia = 0;
		if(timKiemDTO.getIdMau() == 0 || timKiemDTO.getSize() == null) {
			minGia = sanPhamDAO.findMinGiaByLoaiSanPham(tenLoai);
			maxGia = sanPhamDAO.findMaxGiaByLoaiSanPham(tenLoai);
		}
		else if(timKiemDTO.getIdMau() == 0) {
			minGia = sanPhamDAO.findMinGiaByLoaiSanPhamAndSize(tenLoai, timKiemDTO.getSize());
			maxGia = sanPhamDAO.findMaxGiaByLoaiSanPhamAndSize(tenLoai, timKiemDTO.getSize());
		}
		else if(timKiemDTO.getSize() == null) {
			minGia = sanPhamDAO.findMinGiaByLoaiSanPhamAndMau(tenLoai, timKiemDTO.getIdMau());
			maxGia = sanPhamDAO.findMaxGiaByLoaiSanPhamAndMau(tenLoai, timKiemDTO.getIdMau());
		}
		else {
			minGia = sanPhamDAO.findMinGiaByLoaiSanPhamAndSizeAndMau(tenLoai, timKiemDTO.getSize(), timKiemDTO.getIdMau());
			maxGia = sanPhamDAO.findMaxGiaByLoaiSanPhamAndSizeAndMau(tenLoai, timKiemDTO.getSize(), timKiemDTO.getIdMau());
		}
		giaDTO.setMaxGia(maxGia);
		giaDTO.setMinGia(minGia);
		
		return giaDTO;
	}
	
	public DanhSachSanPhamDTO findSanPhamByTenSanPham(String tenSanPham,int n) {
		String s = "%"+tenSanPham+"%";
		System.out.println(s);
		List<SanPham> sanPhams = sanPhamDAO.findSanPhamByTenSanPham(s, n);
		System.out.println(sanPhams.size());
		List<SanPhamDTO> sanPhamDTOs = new ArrayList<SanPhamDTO>();
		for (SanPham sanPham : sanPhams) {
			SanPhamDTO sanPhamDTO = chuyenDoiSanPhamSangSanPhamDTO2(sanPham);
			sanPhamDTOs.add(sanPhamDTO);
		}
		DanhSachSanPhamDTO danhSachSanPhamDTO = new DanhSachSanPhamDTO();
		danhSachSanPhamDTO.setSanPhamDTOs(sanPhamDTOs);
		if(sanPhamDTOs.size()<12) {
			danhSachSanPhamDTO.setCheck(false);
		}
		else {
			danhSachSanPhamDTO.setCheck(true);
		}
		return danhSachSanPhamDTO;
	}
	
	public DanhSachSanPhamAdmin findSanPham() {
		DanhSachTimKiemDTO danhSachTimKiemDTO = new DanhSachTimKiemDTO();
		List<SanPham> sanPhams = sanPhamDAO.getListSanPhamFrom(0);
		List<SanPhamDTO> sanPhamDTOs = new ArrayList<SanPhamDTO>();
		for (SanPham sanPham : sanPhams) {
			SanPhamDTO sanPhamDTO = chuyenDoiSanPhamSangSanPhamDTO2(sanPham);
			sanPhamDTOs.add(sanPhamDTO);
		}
		DanhSachSanPhamDTO danhSachSanPhamDTO = new DanhSachSanPhamDTO();
		danhSachSanPhamDTO.setSanPhamDTOs(sanPhamDTOs);
		DanhSachMauDTO danhSachMauDTO = mauService.findAllMau();
		DanhSachSizeDTO danhSachSizeDTO = chiTietSanPhamService.getAllSize();
		danhSachTimKiemDTO.setDanhSachMauDTO(danhSachMauDTO);
		danhSachTimKiemDTO.setDanhSachSanPhamDTO(danhSachSanPhamDTO);
		danhSachTimKiemDTO.setDanhSachSizeDTO(danhSachSizeDTO);
		int minGia = sanPhamDAO.getMinGiaTien();
		int maxGia = sanPhamDAO.getMaxGiaTien();
		GiaDTO giaDTO = new GiaDTO();
		giaDTO.setMaxGia(maxGia);
		giaDTO.setMinGia(minGia);
		danhSachTimKiemDTO.setGiaDTO(giaDTO);
		DanhSachSanPhamAdmin danhSachSanPhamAdmin = new DanhSachSanPhamAdmin();
		danhSachSanPhamAdmin.setDanhSachTimKiemDTO(danhSachTimKiemDTO);
		danhSachSanPhamAdmin.setSoLuongSanPham(sanPhamDAO.getSoLuongSanPham());
		danhSachSanPhamAdmin.setSoLuongHang(chiTietSanPhamService.getSoLuongHang());
		return danhSachSanPhamAdmin;
	}
	
	public DanhSachSanPhamDTO findSanPham(TimKiemDTO timKiemDTO,int n) {
		DanhSachSanPhamDTO danhSachSanPhamDTO = new DanhSachSanPhamDTO();
		List<SanPhamDTO> sanPhamDTOs = new ArrayList<SanPhamDTO>();
		List<SanPham> sanPhams = sanPhamDAO.findSanPham(timKiemDTO.getTenLoai(), timKiemDTO.getSize(), timKiemDTO.getMinGia(), timKiemDTO.getMaxGia(), timKiemDTO.getIdMau(), n);
		for (SanPham sanPham : sanPhams) {
			SanPhamDTO sanPhamDTO = chuyenDoiSanPhamSangSanPhamDTO2(sanPham);
			sanPhamDTOs.add(sanPhamDTO);
		}
		if(sanPhams.size() <12) {
			danhSachSanPhamDTO.setCheck(false);
		}
		else {
			danhSachSanPhamDTO.setCheck(true);
		}
		danhSachSanPhamDTO.setSanPhamDTOs(sanPhamDTOs);
		return danhSachSanPhamDTO;
	}
	
	
	private SanPhamDTO chuyenDoiSanPhamSangSanPhamDTO(SanPham sanPham) {
		SanPhamDTO sanPhamDTO = new SanPhamDTO();
		LoaiSanPhamDTO loaiSanPhamDTO =new LoaiSanPhamDTO();
		loaiSanPhamDTO.setId(sanPham.getLoaiSanPham().getId());
		loaiSanPhamDTO.setTenLoai(sanPham.getLoaiSanPham().getTenLoai());
		sanPhamDTO.setChatLieu(sanPham.getChatLieu());
		sanPhamDTO.setGiaTien(sanPham.getGiaTien());
		sanPhamDTO.setId(sanPham.getId());
		sanPhamDTO.setLoaiSanPhamDTO(loaiSanPhamDTO);
		sanPhamDTO.setLuotXem(sanPham.getLuotXem());
		sanPhamDTO.setMoTa(sanPham.getMoTa());
		sanPhamDTO.setTenSanPham(sanPham.getTenSanPham());
		List<ChiTietSanPhamDTO> chiTietSanPhamDTOs= new ArrayList<ChiTietSanPhamDTO>();
		List<ChiTietSanPham> chiTietSanPhams = sanPham.getChiTietSanPhams();
		for (ChiTietSanPham chiTietSanPham : chiTietSanPhams) {
			ChiTietSanPhamDTO chiTietSanPhamDTO = new ChiTietSanPhamDTO();
			chiTietSanPhamDTO.setId(chiTietSanPham.getId());
			chiTietSanPhamDTO.setIdSanPham(sanPham.getId());
			MauDTO mauDTO = new MauDTO();
			Mau mau = chiTietSanPham.getMau();
			mauDTO.setId(mau.getId());
			mauDTO.setMaMau(mau.getMaMau());
			mauDTO.setTenMau(mau.getTenMau());
			chiTietSanPhamDTO.setMauDTO(mauDTO);
			chiTietSanPhamDTO.setSoLuong(chiTietSanPham.getSoLuong());
			chiTietSanPhamDTO.setSize(chiTietSanPham.getSize());
			chiTietSanPhamDTOs.add(chiTietSanPhamDTO);
		}
		int km = khuyenMaiService.getKhuyenMaiByIdSanPham(sanPham.getId());
		sanPhamDTO.setKhuyenMai(km);
		sanPhamDTO.setChiTietSanPhamDTOs(chiTietSanPhamDTOs);
		DanhSachHinhAnhDTO danhSachHinhAnhDTO = hinhAnhService.getHinhAnhByIdSanPham(sanPham.getId());
		sanPhamDTO.setHinhAnhDTOs(danhSachHinhAnhDTO.getHinhAnhDTOs());
		return sanPhamDTO;
	}
	
	private SanPhamDTO chuyenDoiSanPhamSangSanPhamDTO2(SanPham sanPham) {
		SanPhamDTO sanPhamDTO = new SanPhamDTO();
		LoaiSanPhamDTO loaiSanPhamDTO =new LoaiSanPhamDTO();
		loaiSanPhamDTO.setId(sanPham.getLoaiSanPham().getId());
		loaiSanPhamDTO.setTenLoai(sanPham.getLoaiSanPham().getTenLoai());
		sanPhamDTO.setChatLieu(sanPham.getChatLieu());
		sanPhamDTO.setGiaTien(sanPham.getGiaTien());
		sanPhamDTO.setId(sanPham.getId());
		sanPhamDTO.setLoaiSanPhamDTO(loaiSanPhamDTO);
		sanPhamDTO.setLuotXem(sanPham.getLuotXem());
		sanPhamDTO.setMoTa(sanPham.getMoTa());
		sanPhamDTO.setTenSanPham(sanPham.getTenSanPham());
		int km = khuyenMaiService.getKhuyenMaiByIdSanPham(sanPham.getId());
		sanPhamDTO.setKhuyenMai(km);
		DanhSachHinhAnhDTO danhSachHinhAnhDTO = hinhAnhService.getHinhAnhByIdSanPham(sanPham.getId());
		sanPhamDTO.setHinhAnhDTOs(danhSachHinhAnhDTO.getHinhAnhDTOs());
		return sanPhamDTO;
	}
	
	private SanPham chuyenSanPhamDTOSangSanPham(SanPhamDTO sanPhamDTO) {
		SanPham sanPham = new SanPham();
		sanPham.setChatLieu(sanPhamDTO.getChatLieu());
		List<ChiTietSanPham> chiTietSanPhams = new ArrayList<ChiTietSanPham>();
		List<ChiTietSanPhamDTO> chiTietSanPhamDTOs = sanPhamDTO.getChiTietSanPhamDTOs();
		System.out.println(chiTietSanPhamDTOs.size());
		for (ChiTietSanPhamDTO chiTietSanPhamDTO : chiTietSanPhamDTOs) {
			ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
			chiTietSanPham.setId(chiTietSanPhamDTO.getId());
			Mau mau = mauService.getMauById((chiTietSanPhamDTO.getMauDTO().getId()));
			if(mau == null) {
				mau.setId(chiTietSanPhamDTO.getMauDTO().getId());
				mau.setMaMau(chiTietSanPhamDTO.getMauDTO().getMaMau());
				mau.setTenMau(chiTietSanPhamDTO.getMauDTO().getTenMau());
			}
			chiTietSanPham.setMau(mau);
			chiTietSanPham.setSanPham(sanPham);
			chiTietSanPham.setSize(chiTietSanPhamDTO.getSize());
			chiTietSanPham.setSoLuong(chiTietSanPhamDTO.getSoLuong());
			chiTietSanPhams.add(chiTietSanPham);
		}
		sanPham.setChiTietSanPhams(chiTietSanPhams);
		sanPham.setGiaTien(sanPham.getGiaTien());
		sanPham.setId(sanPhamDTO.getId());
		LoaiSanPham loaiSanPham = new LoaiSanPham();
		loaiSanPham.setId(sanPhamDTO.getLoaiSanPhamDTO().getId());
		sanPham.setLoaiSanPham(loaiSanPham);
		sanPham.setLuotXem(sanPhamDTO.getLuotXem());
		sanPham.setMoTa(sanPhamDTO.getMoTa());
		sanPham.setTenSanPham(sanPhamDTO.getTenSanPham());
		return sanPham;
	}
}
