package com.nk.hinhanhService.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nk.hinhanhService.entity.HinhAnh;

@Transactional
@Repository
public interface HinhAnhDAO extends CrudRepository<HinhAnh, Integer>{

	@Query(value = "SELECT * FROM hinhanh.hinhanh\r\n" + 
			"where hinhanh.idSanPham = :idSanPham",
			nativeQuery = true)
	public List<HinhAnh> getHinhAnhByIdSanPham(@Param(value = "idSanPham") int idSanPham);
	
	@Query(value = "SELECT * FROM hinhanh.hinhanh\r\n" + 
			"where hinhanh.idSanPham = :idSanPham \r\n"
			+ "and hinhanh.idMau = :idMau ;",
			nativeQuery = true)
	public HinhAnh getHinhAnhByIdSanPhamAndIdMau(@Param(value = "idSanPham") int idSanPham,@Param(value = "idMau") int idMau);
	
	@Modifying
	@Query(value = "DELETE FROM hinhanh.hinhanh\r\n" + 
			"where hinhanh.idSanPham = :idSanPham ;",
			nativeQuery = true)
	public void xoaHinhAnh(@Param(value = "idSanPham") int idSanPham);
}
