package com.nk.khuyenmaiService.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "chitietkhuyenmai")
public class ChiTietKhuyenMai implements Serializable{

	public ChiTietKhuyenMai() {
		super();
	}

	public ChiTietKhuyenMai(int id, KhuyenMai khuyenMai, int idSanPham) {
		super();
		this.id = id;
		this.khuyenMai = khuyenMai;
		this.idSanPham = idSanPham;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3274079576341874098L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "idkhuyenmai")
	private KhuyenMai khuyenMai;
	
	@Column(name = "idsanpham")
	private int idSanPham;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}

	public int getIdSanPham() {
		return idSanPham;
	}

	public void setIdSanPham(int idSanPham) {
		this.idSanPham = idSanPham;
	}
	
	
}
