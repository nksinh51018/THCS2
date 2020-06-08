package com.nk.sanphamService.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nk.sanphamService.entity.Mau;

public interface MauDAO extends CrudRepository<Mau, Integer>{

	@Query(value = "SELECT DISTINCT m.* FROM sanpham.sanpham sp, sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp, sanpham.mau m\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and m.id = ctsp.idMau\r\n" + 
			"and sp.GiaTien > :minGia \r\n" + 
			"and sp.GiaTien < :maxGia \r\n" + 
			"and lsp.TenLoai= :tenLoai \r\n" + 
			"and ctsp.Size = :size ;", 
			nativeQuery = true)
	public List<Mau> findMauByLoaiSanPhamAndGiaTienAndSize(@Param(value = "tenLoai") String tenLoai,
															@Param(value = "minGia") int minGia,
															@Param(value = "maxGia") int maxGia,
															@Param(value = "size") String size);
	
	@Query(value = "SELECT DISTINCT m.* FROM sanpham.sanpham sp, sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp, sanpham.mau m\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and m.id = ctsp.idMau\r\n" + 
			"and sp.GiaTien > :minGia \r\n" + 
			"and sp.GiaTien < :maxGia \r\n" + 
			"and lsp.TenLoai= :tenLoai \r\n" + 
			";", 
			nativeQuery = true)
	public List<Mau> findMauByLoaiSanPhamAndGiaTien(@Param(value = "tenLoai") String tenLoai,
													@Param(value = "minGia") int minGia,
													@Param(value = "maxGia") int maxGia);
	
	@Query(value = "SELECT DISTINCT m.* FROM sanpham.sanpham sp, sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp, sanpham.mau m\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and m.id = ctsp.idMau\r\n" + 
			"and lsp.TenLoai= :tenLoai \r\n" + 
			"and ctsp.Size = :size ;", 
			nativeQuery = true)
	public List<Mau> findMauByLoaiSanPhamAndSize(@Param(value = "tenLoai") String tenLoai,
												@Param(value = "size") String size);
	
	@Query(value = "SELECT DISTINCT m.* FROM sanpham.sanpham sp, sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp, sanpham.mau m\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and m.id = ctsp.idMau\r\n" + 
			"and lsp.TenLoai= :tenLoai \r\n" + 
			";", 
			nativeQuery = true)
	public List<Mau> findMauByLoaiSanPham(@Param(value = "tenLoai") String tenLoai);
	
	@Query(value = "SELECT * FROM sanpham.mau m\r\n" + 
			"where m.TenMau= :tenMau \r\n;", 
			nativeQuery = true)
	public Mau findMauByTenMau(@Param(value = "tenMau") String tenMau);
	
	@Query(value = "SELECT * FROM sanpham.mau m\r\n" + 
			"where m.id= :id \r\n;", 
			nativeQuery = true)
	public Mau findMauById(@Param(value = "id") int id);
}
