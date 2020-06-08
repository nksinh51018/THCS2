package com.nk.sanphamService.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "mau")
public class Mau implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 807046821287767696L;
	@Override
	public String toString() {
		return "Mau [id=" + id + ", tenMau=" + tenMau + ", maMau=" + maMau + ", chiTietSanPhams=" + chiTietSanPhams
				+ "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "tenmau")
	private String tenMau;
	
	@Column(name = "mamau")
	private String maMau;

	@OneToMany(mappedBy = "mau")
	private List<ChiTietSanPham> chiTietSanPhams;
	
	public List<ChiTietSanPham> getChiTietSanPhams() {
		return chiTietSanPhams;
	}

	public void setChiTietSanPhams(List<ChiTietSanPham> chiTietSanPhams) {
		this.chiTietSanPhams = chiTietSanPhams;
	}

	public Mau() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenMau() {
		return tenMau;
	}

	public void setTenMau(String tenMau) {
		this.tenMau = tenMau;
	}

	public String getMaMau() {
		return maMau;
	}

	public void setMaMau(String maMau) {
		this.maMau = maMau;
	}

	
	

}
