package com.nk.dto;

import java.util.List;

public class DanhSachHoaDonDTO {

	private List<HoaDonDTO> hoaDonDTOs;
	private boolean check;
	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public List<HoaDonDTO> getHoaDonDTOs() {
		return hoaDonDTOs;
	}

	public void setHoaDonDTOs(List<HoaDonDTO> hoaDonDTOs) {
		this.hoaDonDTOs = hoaDonDTOs;
	}

	public DanhSachHoaDonDTO() {
		super();
	}

	@Override
	public String toString() {
		return "DanhSachHoaDon [hoaDonDTOs=" + hoaDonDTOs + "]";
	}
	
	
	
}
