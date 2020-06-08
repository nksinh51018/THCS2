package com.nk.khuyenmaiService.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nk.khuyenmaiService.entity.ChiTietKhuyenMai;

public interface ChiTietKhuyenMaiDAO extends CrudRepository<ChiTietKhuyenMai, Integer>{

	@Query(value = "SELECT ctkm.idSanPham FROM khuyenmai.chitietkhuyenmai ctkm\r\n" + 
			"where ctkm.idKhuyenMai = :idKhuyenMai ;",
			nativeQuery = true)
	public List<Integer> getListIdSanPhamByIdKhuyenMai(@Param(value = "idKhuyenMai") int idKhuyenMai);
	
	@Query(value = "SELECT * FROM khuyenmai.chitietkhuyenmai ctkm\r\n" + 
			"where ctkm.idKhuyenMai = :idKhuyenMai \r\n"
			+ "and ctkm.idSanPham = :idSanPham ;",
			nativeQuery = true)
	public ChiTietKhuyenMai getChiTietByIdKhuyenMaiAndIdSanPham(@Param(value = "idKhuyenMai") int idKhuyenMai,@Param(value = "idSanPham") int idSanPham);
}
