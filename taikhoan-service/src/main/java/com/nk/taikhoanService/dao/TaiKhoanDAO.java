package com.nk.taikhoanService.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nk.taikhoanService.entity.TaiKhoan;

public interface TaiKhoanDAO extends CrudRepository<TaiKhoan, Integer>{

	public List<TaiKhoan> getTaiKhoanByTenDangNhap(String tenDangNhap);
	

	@Query(value = "SELECT * FROM nguoidung.nguoidung\r\n" + 
			"limit 12\r\n" +
			"offset :n ;",
			nativeQuery = true)
	public List<TaiKhoan> getAllTaiKhoan(@Param(value = "n")int n);
	
	@Query(value = "SELECT * FROM nguoidung.nguoidung nd\r\n" + 
			"WHERE ((nd.NgaySinh between :ngayDatDau and :ngayKetThuc) or :ngayDatDau is null) \r\n"+
			"limit 12\r\n" +
			"offset :n ;",
			nativeQuery = true)
	public List<TaiKhoan> findTaiKhoan(@Param(value = "n")int n,@Param(value = "ngayDatDau") LocalDate ngayDatDau,@Param(value = "ngayKetThuc") LocalDate ngayKetThuc);
	
	@Query(value = "SELECT * FROM nguoidung.nguoidung nd\r\n" + 
			"WHERE nd.TenDangNhap like :tenDangNhap \r\n"+
			"limit 12\r\n" +
			"offset :n ;",
			nativeQuery = true)
	public List<TaiKhoan> findTaiKhoanByTenDangNhap(@Param(value = "n")int n,@Param(value = "tenDangNhap") String tenDangNhap);
}
