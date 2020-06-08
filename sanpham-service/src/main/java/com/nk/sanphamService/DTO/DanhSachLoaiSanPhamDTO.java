package com.nk.sanphamService.DTO;

import java.util.ArrayList;
import java.util.List;

public class DanhSachLoaiSanPhamDTO {

	private List<LoaiSanPhamDTO> loaiSanPhamDTOs;

	public List<LoaiSanPhamDTO> getLoaiSanPhamDTOs() {
		return loaiSanPhamDTOs;
	}

	public void setLoaiSanPhamDTOs(List<LoaiSanPhamDTO> loaiSanPhamDTOs) {
		this.loaiSanPhamDTOs = loaiSanPhamDTOs;
	}
	
	public DanhSachLoaiSanPhamDTO() {
		loaiSanPhamDTOs = new ArrayList<LoaiSanPhamDTO>();
	}
	
	
}
