package com.nk.hoadonService.dto;

import java.time.LocalDate;

public class TimKiemHoaDonDTO {

	private LocalDate ngayBatDau;
	private LocalDate ngayKetThuc;
	private int trangThai;
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
	public int getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	@Override
	public String toString() {
		return "TimKiemHoaDonDTO [ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", trangThai="
				+ trangThai + "]";
	}
	
	
	
}
