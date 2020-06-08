package com.nk.sanphamService.DTO;

import java.io.Serializable;

public class TimKiemDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3002452615592866766L;
	private int minGia;
	private int maxGia;
	private int idMau;
	private String size;
	private String tenLoai;
	
	
	public String getTenLoai() {
		return tenLoai;
	}
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}
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
	
	
	public TimKiemDTO() {
		super();
	}
	public TimKiemDTO(int minGia, int maxGia, int idMau, String size) {
		super();
		this.minGia = minGia;
		this.maxGia = maxGia;
		this.idMau = idMau;
		this.size = size;
	}
	
	
	
}
