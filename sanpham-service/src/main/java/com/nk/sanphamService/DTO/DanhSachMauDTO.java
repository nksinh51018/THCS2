package com.nk.sanphamService.DTO;

import java.util.ArrayList;
import java.util.List;

public class DanhSachMauDTO {

	private List<MauDTO> mauDTOs;

	public List<MauDTO> getMauDTOs() {
		return mauDTOs;
	}

	public void setMauDTOs(List<MauDTO> mauDTOs) {
		this.mauDTOs = mauDTOs;
	}
	
	public DanhSachMauDTO() {
		mauDTOs = new ArrayList<MauDTO>();
	}
	
}
