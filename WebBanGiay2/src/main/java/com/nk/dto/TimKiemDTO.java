package com.nk.dto;

public class TimKiemDTO {

	private int minGia;
	private int maxGia;
	private int idMau;
	private String size;
	public int getMinGia() {
		return minGia;
	}
	public void setMinGia(int minGia) {
		this.minGia = minGia;
	}
	public int getMaxGia() {
		return maxGia;
	}
	public void setMaxGia(int maxGia) {
		this.maxGia = maxGia;
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
	@Override
	public String toString() {
		return "TimKiemDTO [minGia=" + minGia + ", maxGia=" + maxGia + ", idMau=" + idMau + ", size=" + size + "]";
	}
	
	
	
}
