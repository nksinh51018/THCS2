package com.nk.sanphamService.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nk.sanphamService.entity.ChiTietSanPham;

@Transactional
@Repository
public interface ChiTietSanPhamDAO extends CrudRepository<ChiTietSanPham, Integer>{

	@Query(value = "SELECT DISTINCT ctsp.Size FROM sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp,sanpham.sanpham sp\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and lsp.TenLoai = :tenLoai ;",
			nativeQuery = true)
	public List<String> findSizeByLoaiSanPham(@Param(value = "tenLoai") String tenLoai);
	
	@Query(value = "SELECT DISTINCT ctsp.Size FROM sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp,sanpham.sanpham sp, sanpham.mau m\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and ctsp.idMau = m.id\r\n" + 
			"and lsp.TenLoai = :tenLoai \r\n" + 
			"and m.id = :idMau ;",
			nativeQuery = true)
	public List<String> findSizeByLoaiSanPhamAndMau(@Param(value = "tenLoai") String tenLoai,
												@Param(value = "idMau") int idMau);
	
	
	@Query(value = "SELECT DISTINCT ctsp.Size FROM sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp,sanpham.sanpham sp, sanpham.mau m\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and ctsp.idMau = m.id\r\n" + 
			"and lsp.TenLoai = :tenLoai \r\n" + 
			"and sp.GiaTien > :minGia \r\n" + 
			"and sp.GiaTien < :maxGia ;",
			nativeQuery = true)
	public List<String> findSizeByLoaiSanPhamAndGia(@Param(value = "tenLoai") String tenLoai,
												@Param(value = "minGia") int minGia,
												@Param(value = "maxGia") int maxGia);
	
	@Query(value = "SELECT DISTINCT ctsp.Size FROM sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp,sanpham.sanpham sp, sanpham.mau m\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and ctsp.idMau = m.id\r\n" + 
			"and lsp.TenLoai = :tenLoai \r\n" + 
			"and sp.GiaTien > :minGia \r\n" + 
			"and sp.GiaTien < :maxGia \r\n" +
			"and m.id = :idMau ;",
			nativeQuery = true)
	public List<String> findSizeByLoaiSanPhamAndGiaAndMau(@Param(value = "tenLoai") String tenLoai,
												@Param(value = "minGia") int minGia,
												@Param(value = "maxGia") int maxGia,
												@Param(value = "idMau") int idMau);
	
	
	@Query(value = "SELECT * FROM sanpham.chitietsanpham\r\n" + 
			"where idSanPham= :idSanPham \r\n" + 
			"and idMau = :idMau \r\n" + 
			"and size = :size ;",
			nativeQuery = true)
	public int getIdChiTietSanPhamByIdSanPhamAndIdMauAndSize(@Param(value = "idSanPham") int idSanPham,
												@Param(value = "idMau") int idMau,
												@Param(value = "size") String size);
	
	@Query(value = "SELECT * FROM sanpham.chitietsanpham\r\n" + 
			"where idSanPham= :idSanPham \r\n" + 
			"and idMau = :idMau \r\n" + 
			"and size = :size ;",
			nativeQuery = true)
	public ChiTietSanPham getChiTietSanPhamByIdSanPhamAndIdMauAndSize(@Param(value = "idSanPham") int idSanPham,
												@Param(value = "idMau") int idMau,
												@Param(value = "size") String size);
	@Query(value = "SELECT DISTINCT ctsp.Size FROM sanpham.chitietsanpham ctsp;",
			nativeQuery = true)
	public List<String> findAllSize();
	
	@Query(value = "SELECT sum(ctsp.SoLuong) FROM sanpham.chitietsanpham ctsp;",
			nativeQuery = true)
	public int getAllSoLuongHang();
	
	@Query(value = "SELECT * FROM sanpham.chitietsanpham ctsp\r\n"
			+ "limit 12 \r\n" + 
			"offset :n ;",
			nativeQuery = true)
	public List<ChiTietSanPham> getAllChiTiet(@Param(value = "n") int n);
}
