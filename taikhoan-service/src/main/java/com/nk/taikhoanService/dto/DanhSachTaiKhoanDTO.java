package com.nk.taikhoanService.dto;

import java.util.List;

public class DanhSachTaiKhoanDTO {

	private List<TaiKhoanDTO> taiKhoanDTOs;
	
	private boolean check;

	public List<TaiKhoanDTO> getTaiKhoanDTOs() {
		return taiKhoanDTOs;
	}

	public void setTaiKhoanDTOs(List<TaiKhoanDTO> taiKhoanDTOs) {
		this.taiKhoanDTOs = taiKhoanDTOs;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}
	
	
	
	
}
