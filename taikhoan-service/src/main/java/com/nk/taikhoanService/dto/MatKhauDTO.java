package com.nk.taikhoanService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MatKhauDTO {

	@JsonProperty
	private String matKhauCu;
	
	private String matKhauMoi;
	
	public String getMatkhauCu() {
		return matKhauCu;
	}
	public void setMatkhauCu(String matkhauCu) {
		this.matKhauCu = matkhauCu;
	}
	public String getMatKhauMoi() {
		return matKhauMoi;
	}
	public void setMatKhauMoi(String matKhauMoi) {
		this.matKhauMoi = matKhauMoi;
	}
	public MatKhauDTO() {
		super();
	}
	@Override
	public String toString() {
		return "MatKhauDTO [matKhauCu=" + matKhauCu + ", matKhauMoi=" + matKhauMoi + "]";
	}
	public MatKhauDTO(String matKhauCu, String matKhauMoi) {
		super();
		this.matKhauCu = matKhauCu;
		this.matKhauMoi = matKhauMoi;
	}
}
