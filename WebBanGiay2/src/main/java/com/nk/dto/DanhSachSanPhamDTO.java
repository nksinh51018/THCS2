package com.nk.dto;

import java.util.ArrayList;
import java.util.List;

public class DanhSachSanPhamDTO {
	
	private boolean check;
	
	
	private List<SanPhamDTO> sanPhamDTOs;

	
	public List<SanPhamDTO> getSanPhamDTOs() {
		return sanPhamDTOs;
	}


	public void setSanPhamDTOs(List<SanPhamDTO> sanPhamDTOs) {
		this.sanPhamDTOs = sanPhamDTOs;
	}


	public DanhSachSanPhamDTO() {
		super();
		sanPhamDTOs = new ArrayList<SanPhamDTO>();
		check = true;
	}


	public boolean isCheck() {
		return check;
	}


	public void setCheck(boolean check) {
		this.check = check;
	}
	
	
}
