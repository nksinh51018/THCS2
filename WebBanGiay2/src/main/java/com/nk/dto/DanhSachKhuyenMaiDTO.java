package com.nk.dto;

import java.util.List;

public class DanhSachKhuyenMaiDTO {

	private List<KhuyenMaiDTO> khuyenMaiDTOs;
	private boolean check;
	public List<KhuyenMaiDTO> getKhuyenMaiDTOs() {
		return khuyenMaiDTOs;
	}
	public void setKhuyenMaiDTOs(List<KhuyenMaiDTO> khuyenMaiDTOs) {
		this.khuyenMaiDTOs = khuyenMaiDTOs;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	
	
	
}
