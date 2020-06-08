package com.nk.khuyenmaiService.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nk.khuyenmaiService.entity.KhuyenMai;

public interface KhuyenMaiDAO extends CrudRepository<KhuyenMai, Integer>{

	@Query(value = "SELECT km.id, km.TenKhuyenMai,km.NgayBatDau,km.NgayKetThuc,km.PhanTram FROM khuyenmai.khuyenmai km, khuyenmai.chitietkhuyenmai ctkm\r\n" + 
			"where ctkm.idSanPham = :id \r\n" + 
			"and now() between km.NgayBatDau and km.NgayKetThuc \r\n"+
			"and ctkm.idKhuyenMai = km.id;",
			nativeQuery = true)
	public List<KhuyenMai> getKhuyenMaiByIDSanPham(@Param(value = "id") int id);
	
	@Query(value = "SELECT * FROM khuyenmai.khuyenmai\r\n" + 
			"limit 12\r\n" + 
			"offset :n ;",
			nativeQuery = true)
	public List<KhuyenMai> getAllKhuyenMai(@Param(value = "n") int n);
	
	@Query(value = "SELECT * FROM khuyenmai.khuyenmai km\r\n" + 
			"WHERE (( :now between km.NgayBatDau and km.NgayKetThuc) or :now is null) \r\n"+
			"and (( :now2 not between km.NgayBatDau and km.NgayKetThuc) or :now2 is null) \r\n"+
			"limit 12\r\n" + 
			"offset :n ;",
			nativeQuery = true)
	public List<KhuyenMai> findKhuyenMai(@Param(value = "n") int n,@Param(value = "now") LocalDate now,@Param(value = "now2") LocalDate now2);
	
	@Query(value = "SELECT * FROM khuyenmai.khuyenmai km\r\n" + 
			"WHERE km.TenKhuyenMai like :tenKhuyenMai \r\n" +
			"limit 12\r\n" + 
			"offset :n ;",
			nativeQuery = true)
	public List<KhuyenMai> findKhuyenMaiByTenKhuyenMai(@Param(value = "n") int n,@Param(value = "tenKhuyenMai") String tenKhuyenMai);
	
}
