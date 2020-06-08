package com.nk.hinhanhService.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "hinhanh")
public class HinhAnh implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1234655559765935822L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "idsanpham")
	private int idSanPham;
	
	@Column(name = "idmau")
	private int idMau;
	
	@Column(name = "url")
	private String url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdSanPham() {
		return idSanPham;
	}

	public void setIdSanPham(int idSanPham) {
		this.idSanPham = idSanPham;
	}

	public int getIdMau() {
		return idMau;
	}

	public void setIdMau(int idMau) {
		this.idMau = idMau;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public HinhAnh(int id, int idSanPham, int idMau, String url) {
		super();
		this.id = id;
		this.idSanPham = idSanPham;
		this.idMau = idMau;
		this.url = url;
	}

	public HinhAnh() {
		super();
	}

	@Override
	public String toString() {
		return "HinhAnh [id=" + id + ", idSanPham=" + idSanPham + ", idMau=" + idMau + ", url=" + url + "]";
	}
	
}
