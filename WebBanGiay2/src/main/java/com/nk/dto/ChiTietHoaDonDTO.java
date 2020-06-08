package com.nk.dto;

public class ChiTietHoaDonDTO {
	private int id;

	private int idSanPham;
	
	private MauDTO mauDTO;
	
	private String size;

	private int soLuong;

	private int gia;
	
	private String tenSanPham;
	
	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public int getId() {
		return id;
	}
	
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	

	public MauDTO getMauDTO() {
		return mauDTO;
	}

	public void setMauDTO(MauDTO mauDTO) {
		this.mauDTO = mauDTO;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public int getGia() {
		return gia;
	}

	public void setGia(int gia) {
		this.gia = gia;
	}

	public ChiTietHoaDonDTO() {
		super();
	}
	
}
