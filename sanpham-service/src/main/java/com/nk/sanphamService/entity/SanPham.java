package com.nk.sanphamService.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "sanpham")
public class SanPham implements Serializable{
	
	

	@Override
	public String toString() {
		return "SanPham [id=" + id + ", tenSanPham=" + tenSanPham + ", giaTien=" + giaTien + ", moTa=" + moTa
				+ ", chatLieu=" + chatLieu + ", loaiSanPham=" + loaiSanPham + ", luotXem=" + luotXem
				+ ", chiTietSanPhams=" + chiTietSanPhams + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4939327523834873763L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "tensanpham")
	private String tenSanPham;
	
	@Column(name = "giatien")
	private int giaTien;
	
	@Column(name = "mota")
	private String moTa;
	
	@Column(name = "chatlieu")
	private String chatLieu;

	@ManyToOne
	@JoinColumn(name = "idloai")
	private LoaiSanPham loaiSanPham;
	
	@Column(name = "luotxem")
	private int luotXem;
	
	@OneToMany(mappedBy = "sanPham",cascade = CascadeType.ALL)
	private List<ChiTietSanPham> chiTietSanPhams;

	public List<ChiTietSanPham> getChiTietSanPhams() {
		return chiTietSanPhams;
	}

	public void setChiTietSanPhams(List<ChiTietSanPham> chiTietSanPhams) {
		this.chiTietSanPhams = chiTietSanPhams;
	}

	public SanPham() {
		super();
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

	public LoaiSanPham getLoaiSanPham() {
		return loaiSanPham;
	}

	public void setLoaiSanPham(LoaiSanPham loaiSanPham) {
		this.loaiSanPham = loaiSanPham;
	}

	public int getLuotXem() {
		return luotXem;
	}

	public void setLuotXem(int luotXem) {
		this.luotXem = luotXem;
	}

}
