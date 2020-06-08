package com.nk.authService.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "nguoidung")
public class TaiKhoan implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7984550505717855066L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	@Column(name = "tendangnhap")
	private String tenDangNhap;
	
	@Column(name = "matkhau")
	private String matKhau;
	
	@Column(name = "vaitro")
	private String vaiTro;
	
	@Column(name = "ten")
	private String ten;
	
	@Column(name = "gioitinh")
	private int gioiTinh;
	
	@Column(name = "diachi")
	private String diaChi;
	
	@Column(name = "SDT")
	private int sdt;
	
	@Column(name = "ngaysinh")
	private LocalDate ngaySinh;

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

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(String vaiTro) {
		this.vaiTro = vaiTro;
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

	public int getSdt() {
		return sdt;
	}

	public void setSdt(int sdt) {
		this.sdt = sdt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TaiKhoan(int id, String tenDangNhap, String matKhau, String vaiTro, String ten, int gioiTinh, String diaChi,
			int sdt, LocalDate ngaySinh) {
		super();
		this.id = id;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.vaiTro = vaiTro;
		this.ten = ten;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.ngaySinh = ngaySinh;
	}

	public TaiKhoan() {
		super();
	}

	

}
