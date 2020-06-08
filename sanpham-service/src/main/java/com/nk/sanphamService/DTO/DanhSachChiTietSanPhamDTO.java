package com.nk.sanphamService.DTO;

import java.util.List;

public class DanhSachChiTietSanPhamDTO {

	private List<ChiTietSanPhamDTO> chiTietSanPhamDTOs;
	private boolean check;
	
	

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public List<ChiTietSanPhamDTO> getChiTietSanPhamDTOs() {
		return chiTietSanPhamDTOs;
	}

	public void setChiTietSanPhamDTOs(List<ChiTietSanPhamDTO> chiTietSanPhamDTOs) {
		this.chiTietSanPhamDTOs = chiTietSanPhamDTOs;
	}

}
