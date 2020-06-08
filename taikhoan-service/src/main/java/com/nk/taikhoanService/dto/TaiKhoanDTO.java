package com.nk.taikhoanService.dto;

import java.time.LocalDate;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TaiKhoanDTO {

	@Override
	public String toString() {
		return "TaiKhoanDTO [id=" + id + ", tenDangNhap=" + tenDangNhap + ", ten=" + ten + ", gioiTinh=" + gioiTinh
				+ ", diaChi=" + diaChi + ", sdt=" + sdt + ", ngaySinh=" + ngaySinh + ", matKhau=" + matKhau
				+ ", vaiTro=" + vaiTro + "]";
	}

	private int id;
	
	private String tenDangNhap;
	
	private String ten;
	
	private int gioiTinh;

	private String diaChi;
	
	private String sdt;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM-d-yyyy")
	private LocalDate ngaySinh;
	
	private String matKhau;
	
	private String vaiTro;
	
	private int tongTien;
	
	public int getTongTien() {
		return tongTien;
	}

	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}

	public String getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(String vaiTro) {
		this.vaiTro = vaiTro;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public int getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(int gioiTinh) {
		this.gioiTinh = gioiTinh;
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

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public TaiKhoanDTO(int id, String tenDangNhap, String ten, int gioiTinh, String diaChi, String sdt,
			LocalDate ngaySinh) {
		super();
		this.id = id;
		this.tenDangNhap = tenDangNhap;
		this.ten = ten;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.ngaySinh = ngaySinh;
	}

	public TaiKhoanDTO() {
		super();
	}
	
	
}
