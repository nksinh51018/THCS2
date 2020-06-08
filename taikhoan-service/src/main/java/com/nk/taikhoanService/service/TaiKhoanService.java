package com.nk.taikhoanService.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.axis.encoding.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nk.taikhoanService.dao.TaiKhoanDAO;
import com.nk.taikhoanService.dto.DanhSachTaiKhoanDTO;
import com.nk.taikhoanService.dto.TaiKhoanDTO;
import com.nk.taikhoanService.dto.TimKiemTaiKhoanDTO;
import com.nk.taikhoanService.entity.TaiKhoan;

@Service
public class TaiKhoanService {

	@Autowired
	private TaiKhoanDAO taiKhoanDAO;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private HoaDonService hoaDonService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public List<TaiKhoan> getAllTaiKhoan(){
		Iterable<TaiKhoan> iterable =taiKhoanDAO.findAll();
		List<TaiKhoan> taiKhoans = new ArrayList<TaiKhoan>();
		iterable.forEach(taiKhoans::add);
		return taiKhoans;
	}
	
	public TaiKhoanDTO getTaiKhoanByTenDangNhap(String tenDangNhap){
		List<TaiKhoan> taiKhoans = taiKhoanDAO.getTaiKhoanByTenDangNhap(tenDangNhap);
		if(taiKhoans.size()>0) {
			return chuyenTaiKhoanSangTaiKhoanDTO(taiKhoans.get(0));
		}
		return null;
	}
//	
//	public TaiKhoan getTaiKhoanById(int id) {
//		Optional<TaiKhoan> optional =taiKhoanDAO.findById(id);
//		return optional.get();
//	}
	
	public String themTaiKhoan(TaiKhoanDTO taiKhoanDTO) {
		List<TaiKhoan> taiKhoans = taiKhoanDAO.getTaiKhoanByTenDangNhap(taiKhoanDTO.getTenDangNhap());
		if(taiKhoans.size()>0) {
			return "";
		}
		TaiKhoan taiKhoan = chuyenTaiKhoanDTOSangTaiKhoan(taiKhoanDTO);
		try {
			taiKhoan.setMatKhau(encodeString(taiKhoanDTO.getMatKhau()));
			System.out.println(encoder.encode(taiKhoanDTO.getMatKhau()));
			taiKhoanDAO.save(taiKhoan);
			return authService.getToken(taiKhoanDTO.getTenDangNhap(), encodeString(taiKhoanDTO.getMatKhau()));
		} catch (Exception e) {
			return "";
		}
	}
	
	public boolean thayDoiMatKhau(String matKhauCu, String matKhauMoi,String tenDangNhap) {
		try {
			List<TaiKhoan> taiKhoans = taiKhoanDAO.getTaiKhoanByTenDangNhap(tenDangNhap);
			TaiKhoan taiKhoan = taiKhoans.get(0);
			System.out.println(taiKhoan.getMatKhau());
			if(!taiKhoan.getMatKhau().equals(encodeString(matKhauCu))) {
				return false;
			}
			taiKhoan.setMatKhau(matKhauMoi);
			taiKhoanDAO.save(taiKhoan);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean suaTaiKhoan(TaiKhoanDTO taiKhoanDTO) {
		try {
			Optional<TaiKhoan> optional = taiKhoanDAO.findById(taiKhoanDTO.getId());
			TaiKhoan taiKhoan = optional.get();
			taiKhoan.setDiaChi(taiKhoanDTO.getDiaChi());
			taiKhoan.setGioiTinh(taiKhoanDTO.getGioiTinh());
			taiKhoan.setNgaySinh(taiKhoanDTO.getNgaySinh());
			taiKhoan.setSdt(taiKhoanDTO.getSdt());
			taiKhoan.setTen(taiKhoanDTO.getTen());
			taiKhoan.setVaiTro(taiKhoanDTO.getVaiTro());
			taiKhoanDAO.save(taiKhoan);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public int getIdByTenDangNhap(String tenDangNhap) {
		List<TaiKhoan> taiKhoans = taiKhoanDAO.getTaiKhoanByTenDangNhap(tenDangNhap);
		TaiKhoan taiKhoan = taiKhoans.get(0);
		return taiKhoan.getId();
		
	}
	
	
	public DanhSachTaiKhoanDTO getAllTaiKhoan(int n) {
		DanhSachTaiKhoanDTO danhSachTaiKhoanDTO = new DanhSachTaiKhoanDTO();
		
		List<TaiKhoan> taiKhoans = taiKhoanDAO.getAllTaiKhoan(n);
		List<TaiKhoanDTO> taiKhoanDTOs = new ArrayList<TaiKhoanDTO>();
		for (TaiKhoan taiKhoan : taiKhoans) {
			TaiKhoanDTO taiKhoanDTO = chuyenTaiKhoanSangTaiKhoanDTO(taiKhoan);
			taiKhoanDTO.setTongTien(hoaDonService.getTongTienByIdTaiKhoan(taiKhoanDTO.getId()));
			taiKhoanDTOs.add(taiKhoanDTO);
		}
		danhSachTaiKhoanDTO.setTaiKhoanDTOs(taiKhoanDTOs);
		if(taiKhoans.size() <12) {
			danhSachTaiKhoanDTO.setCheck(false);
		}
		else {
			danhSachTaiKhoanDTO.setCheck(true);
		}
		return danhSachTaiKhoanDTO;
	}
	
	public DanhSachTaiKhoanDTO findTaiKhoan(TimKiemTaiKhoanDTO timKiemTaiKhoanDTO,int n) {
		
		DanhSachTaiKhoanDTO danhSachTaiKhoanDTO = new DanhSachTaiKhoanDTO();
		List<TaiKhoan> taiKhoans = taiKhoanDAO.findTaiKhoan(n, timKiemTaiKhoanDTO.getNgayBatDau(), timKiemTaiKhoanDTO.getNgayKetThuc());
		List<TaiKhoanDTO> taiKhoanDTOs = new ArrayList<TaiKhoanDTO>();
		
		if(timKiemTaiKhoanDTO.getTienBatDau() == 0 || timKiemTaiKhoanDTO.getTienKetThuc() ==0) {
			for (TaiKhoan taiKhoan : taiKhoans) {
				int tongTien = hoaDonService.getTongTienByIdTaiKhoan(taiKhoan.getId());
				TaiKhoanDTO taiKhoanDTO = chuyenTaiKhoanSangTaiKhoanDTO(taiKhoan);
				taiKhoanDTO.setTongTien(tongTien);
				taiKhoanDTOs.add(taiKhoanDTO);
			}
		}
		else {
			for (TaiKhoan taiKhoan : taiKhoans) {
				int tongTien = hoaDonService.getTongTienByIdTaiKhoan(taiKhoan.getId());
				System.out.println(tongTien);
				if(tongTien >= timKiemTaiKhoanDTO.getTienBatDau() && tongTien <= timKiemTaiKhoanDTO.getTienKetThuc()) {
					TaiKhoanDTO taiKhoanDTO = chuyenTaiKhoanSangTaiKhoanDTO(taiKhoan);
					taiKhoanDTO.setTongTien(tongTien);
					taiKhoanDTOs.add(taiKhoanDTO);
				}
			}
		}
		if(taiKhoans.size() < 12) {
			danhSachTaiKhoanDTO.setCheck(false);
		}
		else {
			danhSachTaiKhoanDTO.setCheck(true);
		}
		
		danhSachTaiKhoanDTO.setTaiKhoanDTOs(taiKhoanDTOs);
		return danhSachTaiKhoanDTO;
		
	}
	
	public DanhSachTaiKhoanDTO findTaiKhoanByTenDangNhap(String tenDangNhap,int n) {
		String s = "%"+tenDangNhap+"%";
		List<TaiKhoan> taiKhoans = taiKhoanDAO.findTaiKhoanByTenDangNhap(n, s);
		DanhSachTaiKhoanDTO danhSachTaiKhoanDTO = new DanhSachTaiKhoanDTO();
		List<TaiKhoanDTO> taiKhoanDTOs = new ArrayList<TaiKhoanDTO>();
		for (TaiKhoan taiKhoan : taiKhoans) {
			int tongTien = hoaDonService.getTongTienByIdTaiKhoan(taiKhoan.getId());
			TaiKhoanDTO taiKhoanDTO = chuyenTaiKhoanSangTaiKhoanDTO(taiKhoan);
			taiKhoanDTO.setTongTien(tongTien);
			taiKhoanDTOs.add(taiKhoanDTO);
		}
		danhSachTaiKhoanDTO.setTaiKhoanDTOs(taiKhoanDTOs);
		
		if(taiKhoans.size() <12) {
			danhSachTaiKhoanDTO.setCheck(false);
		}
		else {
			danhSachTaiKhoanDTO.setCheck(true);
		}
		
		return danhSachTaiKhoanDTO;
		
	}
	
	public TaiKhoanDTO getTaiKhoanById(int id) {

			Optional<TaiKhoan> optional = taiKhoanDAO.findById(id);
			TaiKhoan taiKhoan = optional.get();
			return chuyenTaiKhoanSangTaiKhoanDTO(taiKhoan);
		
	}
	
	private TaiKhoanDTO chuyenTaiKhoanSangTaiKhoanDTO(TaiKhoan taiKhoan) {
		TaiKhoanDTO taiKhoanDTO = new TaiKhoanDTO();
		taiKhoanDTO.setDiaChi(taiKhoan.getDiaChi());
		taiKhoanDTO.setGioiTinh(taiKhoan.getGioiTinh());
		taiKhoanDTO.setId(taiKhoan.getId());
		taiKhoanDTO.setNgaySinh(taiKhoan.getNgaySinh());
		taiKhoanDTO.setSdt(taiKhoan.getSdt());
		taiKhoanDTO.setTen(taiKhoan.getTen());
		taiKhoanDTO.setTenDangNhap(taiKhoan.getTenDangNhap());
		taiKhoanDTO.setVaiTro(taiKhoan.getVaiTro());
		return taiKhoanDTO;
	}
	
	private TaiKhoan chuyenTaiKhoanDTOSangTaiKhoan(TaiKhoanDTO taiKhoanDTO) {
		TaiKhoan taiKhoan = new TaiKhoan();
		taiKhoan.setDiaChi(taiKhoanDTO.getDiaChi());
		taiKhoan.setGioiTinh(taiKhoanDTO.getGioiTinh());
		taiKhoan.setMatKhau(taiKhoanDTO.getMatKhau());
		taiKhoan.setNgaySinh(taiKhoanDTO.getNgaySinh());
		taiKhoan.setSdt(taiKhoanDTO.getSdt());
		taiKhoan.setTen(taiKhoanDTO.getTen());
		taiKhoan.setTenDangNhap(taiKhoanDTO.getTenDangNhap());
		taiKhoan.setVaiTro(taiKhoanDTO.getVaiTro());
		return taiKhoan;
	}
	
	public String encodeString(String text) throws UnsupportedEncodingException{
		byte[] bytes = text.getBytes("UTF-8");
		String encodeString = Base64.encode(bytes);
		return encodeString;
	}

	// Giải mã hóa một đoạn text (Đã mã hóa trước đó).
	// Decode
	public String decodeString(String encodeText) throws UnsupportedEncodingException {
		byte[] decodeBytes = Base64.decode(encodeText);
		String str = new String(decodeBytes, "UTF-8");
		return str;
	}
}
