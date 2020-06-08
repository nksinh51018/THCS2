package com.nk.khuyenmaiService.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "khuyenmai")
public class KhuyenMai implements Serializable{

	public KhuyenMai() {
		super();
	}
	
	public KhuyenMai(int id, String tenKhuyenMai, int phanTram, LocalDate ngayBatDau, LocalDate ngayKetThuc,
			List<ChiTietKhuyenMai> chiTietKhuyenMais) {
		super();
		this.id = id;
		this.tenKhuyenMai = tenKhuyenMai;
		this.phanTram = phanTram;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.chiTietKhuyenMais = chiTietKhuyenMais;
	}

	public List<ChiTietKhuyenMai> getChiTietKhuyenMais() {
		return chiTietKhuyenMais;
	}
	public void setChiTietKhuyenMais(List<ChiTietKhuyenMai> chiTietKhuyenMais) {
		this.chiTietKhuyenMais = chiTietKhuyenMais;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4229598089934967605L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "tenkhuyenmai")
	private String tenKhuyenMai;
	@Column(name = "phantram")
	private int phanTram;
	@Column(name = "ngaybatdau")
	private LocalDate ngayBatDau;
	@Column(name = "ngayketthuc")
	private LocalDate ngayKetThuc;
	
	@OneToMany(mappedBy = "khuyenMai",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<ChiTietKhuyenMai> chiTietKhuyenMais;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTenKhuyenMai() {
		return tenKhuyenMai;
	}
	public void setTenKhuyenMai(String tenKhuyenMai) {
		this.tenKhuyenMai = tenKhuyenMai;
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
	
}
