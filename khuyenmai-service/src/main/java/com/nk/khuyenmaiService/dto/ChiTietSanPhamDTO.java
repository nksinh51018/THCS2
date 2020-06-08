package com.nk.khuyenmaiService.dto;

public class ChiTietSanPhamDTO {

	
	private int id;
	private int idSanPham;
	private MauDTO mauDTO;
	private int soLuong;
	private String size;
	private String tenSanPham;
	private String url;

	@Override
	public String toString() {
		return "ChiTietSanPhamDTO [id=" + id + ", idSanPham=" + idSanPham + ", mauDTO=" + mauDTO + ", soLuong="
				+ soLuong + ", size=" + size + ", tenSanPham=" + tenSanPham + ", url=" + url + "]";
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
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
