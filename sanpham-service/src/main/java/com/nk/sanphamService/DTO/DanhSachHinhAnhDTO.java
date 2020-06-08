package com.nk.sanphamService.DTO;

import java.util.ArrayList;
import java.util.List;

public class DanhSachHinhAnhDTO {

	private List<HinhAnhDTO> hinhAnhDTOs;

	public List<HinhAnhDTO> getHinhAnhDTOs() {
		return hinhAnhDTOs;
	}

	public void setHinhAnhDTOs(List<HinhAnhDTO> hinhAnhDTOs) {
		this.hinhAnhDTOs = hinhAnhDTOs;
	}

	public DanhSachHinhAnhDTO() {
		super();
		hinhAnhDTOs = new ArrayList<HinhAnhDTO>();
	}
	
	
	
}
