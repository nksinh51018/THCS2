package com.nk.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class KhuyenMaiDTO {

	private int id;
	private int phanTram;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM-d-yyyy")
	private LocalDate ngayBatDau;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM-d-yyyy")
	private LocalDate ngayKetThuc;
	private String tenKhuyenMai;
	
	
	public String getTenKhuyenMai() {
		return tenKhuyenMai;
	}
	public void setTenKhuyenMai(String tenKhuyenMai) {
		this.tenKhuyenMai = tenKhuyenMai;
	}
	private List<ChiTietKhuyenMaiDTO> chiTietKhuyenMaiDTOs;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPhanTram() {
		return phanTram;
	}
	public void setPhanTram(int phanTram) {
		this.phanTram = phanTram;
	}
	public LocalDate getNgayBatDau() {
		return ngayBatDau;
	}
	public void setNgayBatDau(LocalDate ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	public LocalDate getNgayKetThuc() {
		return ngayKetThuc;
	}
	public void setNgayKetThuc(LocalDate ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	public List<ChiTietKhuyenMaiDTO> getChiTietKhuyenMaiDTOs() {
		return chiTietKhuyenMaiDTOs;
	}
	public void setChiTietKhuyenMaiDTOs(List<ChiTietKhuyenMaiDTO> chiTietKhuyenMaiDTOs) {
		this.chiTietKhuyenMaiDTOs = chiTietKhuyenMaiDTOs;
	}
	
	
	
}
