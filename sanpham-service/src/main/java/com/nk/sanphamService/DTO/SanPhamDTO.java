package com.nk.sanphamService.DTO;

import java.util.List;

public class SanPhamDTO{

	@Override
	public String toString() {
		return "SanPhamDTO [id=" + id + ", tenSanPham=" + tenSanPham + ", giaTien=" + giaTien + ", moTa=" + moTa
				+ ", chatLieu=" + chatLieu + ", luotXem=" + luotXem + ", khuyenMai=" + khuyenMai + ", hinhAnhDTOs="
				+ hinhAnhDTOs + ", loaiSanPhamDTO=" + loaiSanPhamDTO + ", chiTietSanPhamDTOs=" + chiTietSanPhamDTOs
				+ "]";
	}

	public SanPhamDTO() {
		super();
	}

	private int id;
	

	private String tenSanPham;
	

	private int giaTien;
	

	private String moTa;
	
	
	private String chatLieu;
	
	private int luotXem;
	
	private int khuyenMai;
	
	private List<HinhAnhDTO> hinhAnhDTOs;
	
	
	
	public List<HinhAnhDTO> getHinhAnhDTOs() {
		return hinhAnhDTOs;
	}

	public void setHinhAnhDTOs(List<HinhAnhDTO> hinhAnhDTOs) {
		this.hinhAnhDTOs = hinhAnhDTOs;
	}

	public int getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(int khuyenMai) {
		this.khuyenMai = khuyenMai;
	}

	private LoaiSanPhamDTO loaiSanPhamDTO;
	
	private List<ChiTietSanPhamDTO> chiTietSanPhamDTOs;

	public List<ChiTietSanPhamDTO> getChiTietSanPhamDTOs() {
		return chiTietSanPhamDTOs;
	}

	public void setChiTietSanPhamDTOs(List<ChiTietSanPhamDTO> chiTietSanPhamDTOs) {
		this.chiTietSanPhamDTOs = chiTietSanPhamDTOs;
	}

	public LoaiSanPhamDTO getLoaiSanPhamDTO() {
		return loaiSanPhamDTO;
	}

	public void setLoaiSanPhamDTO(LoaiSanPhamDTO loaiSanPhamDTO) {
		this.loaiSanPhamDTO = loaiSanPhamDTO;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public int getGiaTien() {
		return giaTien;
	}

	public void setGiaTien(int giaTien) {
		this.giaTien = giaTien;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getChatLieu() {
		return chatLieu;
	}

	public void setChatLieu(String chatLieu) {
		this.chatLieu = chatLieu;
	}

	public int getLuotXem() {
		return luotXem;
	}

	public void setLuotXem(int luotXem) {
		this.luotXem = luotXem;
	}
	
	
}
