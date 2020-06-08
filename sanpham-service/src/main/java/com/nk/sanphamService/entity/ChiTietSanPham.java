package com.nk.sanphamService.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "chitietsanpham")
public class ChiTietSanPham implements Serializable{

	@Override
	public String toString() {
		return "ChiTietSanPham [id=" + id + ", sanPham=" + sanPham + ", mau=" + mau + ", soLuong=" + soLuong + ", size="
				+ size + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -9166473426063017182L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "idsanpham")
	private SanPham sanPham;
	
	@ManyToOne
	@JoinColumn(name = "idmau")
	private Mau mau;
	
	@Column(name = "soluong")
	private int soLuong;
	
	@Column(name = "size")
	private String size;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public Mau getMau() {
		return mau;
	}

	public void setMau(Mau mau) {
		this.mau = mau;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public ChiTietSanPham() {
		super();
	}
	
	
	
}
