package com.nk.hinhanhService.DTO;

public class HinhAnhDTO {

	private int id;

	private int idSanPham;
	
	private int idMau;
	
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

	@Override
	public String toString() {
		return "HinhAnhDTO [id=" + id + ", idSanPham=" + idSanPham + ", idMau=" + idMau + ", url=" + url + "]";
	}
	
	
	
}
