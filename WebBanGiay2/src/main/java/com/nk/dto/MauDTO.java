package com.nk.dto;

public class MauDTO {

	@Override
	public String toString() {
		return "MauDTO [id=" + id + ", tenMau=" + tenMau + ", maMau=" + maMau + "]";
	}
	private int id;
	private String tenMau;
	private String maMau;
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
	public MauDTO() {
		super();
	}
	
	
}
