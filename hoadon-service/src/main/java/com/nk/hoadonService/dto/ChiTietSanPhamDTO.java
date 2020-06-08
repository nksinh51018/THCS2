package com.nk.hoadonService.dto;

public class ChiTietSanPhamDTO {

	
	private int id;
	private int idSanPham;
	private MauDTO mauDTO;
	private int soLuong;
	private String size;
	private String tenSanPham;
	
	public String getTenSanPham() {
		return tenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
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
	public MauDTO getMauDTO() {
		return mauDTO;
	}
	public void setMauDTO(MauDTO mauDTO) {
		this.mauDTO = mauDTO;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	
}
