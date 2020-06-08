package com.nk.taikhoanService.dto;

import java.time.LocalDate;

public class TimKiemTaiKhoanDTO {

	private LocalDate ngayBatDau;
	private LocalDate ngayKetThuc;
	private int tienBatDau;
	private int tienKetThuc;
	public LocalDate getNgayBatDau() {
		return ngayBatDau;
	}
	public void setNgayBatDau(LocalDate ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	public LocalDate getNgayKetThuc() {
		return ngayKetThuc;
	}
	public void setNgayKetThuc(LocalDate ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	public int getTienBatDau() {
		return tienBatDau;
	}
	public void setTienBatDau(int tienBatDau) {
		this.tienBatDau = tienBatDau;
	}
	public int getTienKetThuc() {
		return tienKetThuc;
	}
	public void setTienKetThuc(int tienKetThuc) {
		this.tienKetThuc = tienKetThuc;
	}
	
	
	
}
