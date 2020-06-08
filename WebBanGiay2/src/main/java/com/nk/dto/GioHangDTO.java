package com.nk.dto;

public class GioHangDTO {

	@Override
	public String toString() {
		return "GioHangDTO [idSanPham=" + idSanPham + ", idMau=" + idMau + ", size=" + size + ", soLuong=" + soLuong
				+ ", url=" + url + ", tenMau=" + tenMau + ", gia=" + gia + ", khuyenMai=" + khuyenMai + ", tenSanPham="
				+ tenSanPham + ", maMau=" + maMau + "]";
	}
	private int idSanPham;
	private int idMau;
	private String size;
	private int soLuong;
	private String url;
	private String tenMau;
	private int gia;
	private int khuyenMai;
	private String tenSanPham;
	private String maMau;
	
	
	
	public String getMaMau() {
		return maMau;
	}
	public void setMaMau(String maMau) {
		this.maMau = maMau;
	}
	public String getTenSanPham() {
		return tenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}
	public String getTenMau() {
		return tenMau;
	}
	public void setTenMau(String tenMau) {
		this.tenMau = tenMau;
	}
	public int getGia() {
		return gia;
	}
	public void setGia(int gia) {
		this.gia = gia;
	}
	public int getKhuyenMai() {
		return khuyenMai;
	}
	public void setKhuyenMai(int khuyenMai) {
		this.khuyenMai = khuyenMai;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public GioHangDTO() {
		super();
	}
	
}
