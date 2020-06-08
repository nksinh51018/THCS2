package com.nk.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class HoaDonDTO {

	private int id;
	
	private int idTaiKhoan;

	private String tenNguoiNhan;

	private String diaChi;

	private String sdt;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM-d-yyyy")
	private LocalDate ngayMua;

	private int trangThai;

	private List<ChiTietHoaDonDTO> chiTietHoaDonDTOs;

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "HoaDonDTO [id=" + id + ", idTaiKhoan=" + idTaiKhoan + ", tenNguoiNhan=" + tenNguoiNhan + ", diaChi="
				+ diaChi + ", sdt=" + sdt + ", ngayMua=" + ngayMua + ", trangThai=" + trangThai + ", chiTietHoaDonDTOs="
				+ chiTietHoaDonDTOs + "]";
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdTaiKhoan() {
		return idTaiKhoan;
	}

	public void setIdTaiKhoan(int idTaiKhoan) {
		this.idTaiKhoan = idTaiKhoan;
	}

	public String getTenNguoiNhan() {
		return tenNguoiNhan;
	}

	public void setTenNguoiNhan(String tenNguoiNhan) {
		this.tenNguoiNhan = tenNguoiNhan;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public LocalDate getNgayMua() {
		return ngayMua;
	}

	public void setNgayMua(LocalDate ngayMua) {
		this.ngayMua = ngayMua;
	}



	public List<ChiTietHoaDonDTO> getChiTietHoaDons() {
		return chiTietHoaDonDTOs;
	}

	public void setChiTietHoaDons(List<ChiTietHoaDonDTO> chiTietHoaDonDTOs) {
		this.chiTietHoaDonDTOs = chiTietHoaDonDTOs;
	}

	public HoaDonDTO() {
		super();
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	
}
