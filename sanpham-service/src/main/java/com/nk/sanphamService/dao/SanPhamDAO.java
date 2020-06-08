package com.nk.sanphamService.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nk.sanphamService.entity.SanPham;

public interface SanPhamDAO extends CrudRepository<SanPham, Integer>{

	
	@Query(value = "SELECT * FROM sanpham.sanpham\r\n" + 
			"order by sanpham.LuotXem desc, sanpham.id desc\r\n" + 
			"limit 4;",
			nativeQuery = true)
	public List<SanPham> getListSanPhamHot();
	
	@Query(value = "SELECT * FROM sanpham.sanpham\r\n" + 
			"order by sanpham.id desc\r\n" + 
			"limit 12\r\n" +
			"offset :n ;",
			nativeQuery = true)
	public List<SanPham> getListSanPhamFrom(@Param(value = "n")int n);
	
	@Query(value ="SELECT sp.* FROM sanpham.sanpham sp,sanpham.loaisanpham lsp\r\n" + 
			"where sp.idLoai =lsp.id\r\n" + 
			"and lsp.TenLoai = :tenLoai \r\n" +
			"limit 12\r\n" +
			"offset :n ;",
			nativeQuery = true)
	public List<SanPham> getListSanPhamLoaiFrom(@Param(value = "n")int n,@Param(value = "tenLoai") String tenLoai);
	
	@Query(value = "SELECT min(sp.GiaTien) FROM sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp,sanpham.sanpham sp, sanpham.mau m\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and ctsp.idMau = m.id\r\n" + 
			"and lsp.TenLoai = :tenLoai \r\n;",
			nativeQuery = true)
	public int findMinGiaByLoaiSanPham(@Param(value = "tenLoai") String tenLoai);

	@Query(value = "SELECT min(sp.GiaTien) FROM sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp,sanpham.sanpham sp, sanpham.mau m\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and ctsp.idMau = m.id\r\n" + 
			"and lsp.TenLoai = :tenLoai \r\n"
			+ "and m.id = :idMau ;",
			nativeQuery = true)
	public int findMinGiaByLoaiSanPhamAndMau(@Param(value = "tenLoai") String tenLoai,
											@Param(value = "idMau") int idMau);
	
	@Query(value = "SELECT min(sp.GiaTien) FROM sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp,sanpham.sanpham sp, sanpham.mau m\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and ctsp.idMau = m.id\r\n" + 
			"and lsp.TenLoai = :tenLoai \r\n"
			+ "and ctsp.Size = :size ;",
			nativeQuery = true)
	public int findMinGiaByLoaiSanPhamAndSize(@Param(value = "tenLoai") String tenLoai,
											@Param(value = "size") String size);
	
	@Query(value = "SELECT min(sp.GiaTien) FROM sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp,sanpham.sanpham sp, sanpham.mau m\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and ctsp.idMau = m.id\r\n" + 
			"and lsp.TenLoai = :tenLoai \r\n"
			+ "and ctsp.Size = :size "+
			"and m.id = :idMau ;",
			nativeQuery = true)
	public int findMinGiaByLoaiSanPhamAndSizeAndMau(@Param(value = "tenLoai") String tenLoai,
											@Param(value = "size") String size,
											@Param(value = "idMau") int idMau);
	
	@Query(value = "SELECT max(sp.GiaTien) FROM sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp,sanpham.sanpham sp, sanpham.mau m\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and ctsp.idMau = m.id\r\n" + 
			"and lsp.TenLoai = :tenLoai \r\n;",
			nativeQuery = true)
	public int findMaxGiaByLoaiSanPham(@Param(value = "tenLoai") String tenLoai);

	@Query(value = "SELECT max(sp.GiaTien) FROM sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp,sanpham.sanpham sp, sanpham.mau m\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and ctsp.idMau = m.id\r\n" + 
			"and lsp.TenLoai = :tenLoai \r\n"
			+ "and m.id = :idMau ;",
			nativeQuery = true)
	public int findMaxGiaByLoaiSanPhamAndMau(@Param(value = "tenLoai") String tenLoai,
											@Param(value = "idMau") int idMau);
	
	@Query(value = "SELECT max(sp.GiaTien) FROM sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp,sanpham.sanpham sp, sanpham.mau m\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and ctsp.idMau = m.id\r\n" + 
			"and lsp.TenLoai = :tenLoai \r\n"
			+ "and ctsp.Size = :size ;",
			nativeQuery = true)
	public int findMaxGiaByLoaiSanPhamAndSize(@Param(value = "tenLoai") String tenLoai,
											@Param(value = "size") String size);
	
	@Query(value = "SELECT max(sp.GiaTien) FROM sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp,sanpham.sanpham sp, sanpham.mau m\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and ctsp.idMau = m.id\r\n" + 
			"and lsp.TenLoai = :tenLoai \r\n"
			+ "and ctsp.Size = :size "+
			"and m.id = :idMau ;",
			nativeQuery = true)
	public int findMaxGiaByLoaiSanPhamAndSizeAndMau(@Param(value = "tenLoai") String tenLoai,
											@Param(value = "size") String size,
											@Param(value = "idMau") int idMau
											);
	
	@Query(value = "SELECT DISTINCT sp.* FROM sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp,sanpham.sanpham sp, sanpham.mau m\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and ctsp.idMau = m.id\r\n" + 
			"and lsp.TenLoai = :tenLoai \r\n" +
			"order by sp.id desc\r\n" + 
			"limit 12\r\n" +
			"offset :n ;",
			nativeQuery = true)
	public List<SanPham> findSanPhamByLoaiSanPham(@Param(value = "tenLoai") String tenLoai,
													@Param(value = "n") int n);
	
	@Query(value = "SELECT DISTINCT sp.* FROM sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp,sanpham.sanpham sp, sanpham.mau m\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and ctsp.idMau = m.id\r\n" + 
			"and lsp.TenLoai = :tenLoai \r\n"+
			"and m.id = :idMau \r\n"+
			"order by sp.id desc\r\n" + 
			"limit 12\r\n" +
			"offset :n ;",
			nativeQuery = true)
	public List<SanPham> findSanPhamByLoaiSanPhamAndMau(@Param(value = "tenLoai") String tenLoai,
														@Param(value = "idMau") int idMau,
														@Param(value = "n") int n);
	
	@Query(value = "SELECT DISTINCT sp.* FROM sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp,sanpham.sanpham sp, sanpham.mau m\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and ctsp.idMau = m.id\r\n" + 
			"and lsp.TenLoai = :tenLoai "+
			"and ctsp.Size = :size "+
			"order by sp.id desc\r\n" + 
			"limit 12\r\n" +
			"offset :n ;",
			nativeQuery = true)
	public List<SanPham> findSanPhamByLoaiSanPhamAndSize(@Param(value = "tenLoai") String tenLoai,
														@Param(value = "size") String size,
														@Param(value = "n") int n);
	
	@Query(value = "SELECT DISTINCT sp.* FROM sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp,sanpham.sanpham sp, sanpham.mau m\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and ctsp.idMau = m.id\r\n" + 
			"and lsp.TenLoai = :tenLoai \r\n"+
			"and sp.GiaTien >= :minGia \r\n" + 
			"and sp.GiaTien <= :maxGia \r\n" +
			"order by sp.id desc\r\n" + 
			"limit 12\r\n" +
			"offset :n ;",
			nativeQuery = true)
	public List<SanPham> findSanPhamByLoaiSanPhamAndGia(@Param(value = "tenLoai") String tenLoai,
														@Param(value = "minGia") int minGia,
														@Param(value = "maxGia") int maxGia,
														@Param(value = "n") int n);
	
	@Query(value = "SELECT DISTINCT sp.* FROM sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp,sanpham.sanpham sp, sanpham.mau m\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and ctsp.idMau = m.id\r\n" + 
			"and lsp.TenLoai = :tenLoai \r\n"+
			"and m.id = :idMau " + 
			"and ctsp.Size = :size " +
			"order by sp.id desc\r\n" + 
			"limit 12\r\n" +
			"offset :n ;",
			nativeQuery = true)
	public List<SanPham> findSanPhamByLoaiSanPhamAndMauAndSize(@Param(value = "tenLoai") String tenLoai,
														@Param(value = "idMau") int idMau,
														@Param(value = "size") String size,
														@Param(value = "n") int n);
	
	@Query(value = "SELECT DISTINCT sp.* FROM sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp,sanpham.sanpham sp, sanpham.mau m\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and ctsp.idMau = m.id\r\n" + 
			"and lsp.TenLoai = :tenLoai \r\n"+
			"and m.id = :idMau " + 
			"and sp.GiaTien >= :minGia \r\n" + 
			"and sp.GiaTien <= :maxGia \r\n" +
			"order by sp.id desc\r\n" + 
			"limit 12\r\n" +
			"offset :n ;",
			nativeQuery = true)
	public List<SanPham> findSanPhamByLoaiSanPhamAndMauAndGia(@Param(value = "tenLoai") String tenLoai,
														@Param(value = "idMau") int idMau,
														@Param(value = "minGia") int minGia,
														@Param(value = "maxGia") int maxGia,
														@Param(value = "n") int n);
	
	@Query(value = "SELECT DISTINCT sp.* FROM sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp,sanpham.sanpham sp, sanpham.mau m\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and ctsp.idMau = m.id\r\n" + 
			"and lsp.TenLoai = :tenLoai \r\n"+
			"and sp.GiaTien >= :minGia \r\n" + 
			"and sp.GiaTien <= :maxGia \r\n" +
			"and ctsp.Size = :size " +
			"order by sp.id desc\r\n" + 
			"limit 12\r\n" +
			"offset :n ;",
			nativeQuery = true)
	public List<SanPham> findSanPhamByLoaiSanPhamAndSizeAndGia(@Param(value = "tenLoai") String tenLoai,
														@Param(value = "size") String size,
														@Param(value = "minGia") int minGia,
														@Param(value = "maxGia") int maxGia,
														@Param(value = "n") int n);
	
	@Query(value = "SELECT DISTINCT sp.* FROM sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp,sanpham.sanpham sp, sanpham.mau m\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and ctsp.idMau = m.id\r\n" + 
			"and lsp.TenLoai = :tenLoai \r\n"+
			"and m.id = :idMau " + 
			"and ctsp.Size = :size " +
			"and sp.GiaTien >= :minGia \r\n" + 
			"and sp.GiaTien <= :maxGia \r\n"+
			"order by sp.id desc\r\n" + 
			"limit 12\r\n" +
			"offset :n ;",
			nativeQuery = true)
	public List<SanPham> findSanPhamByLoaiSanPhamAndSizeAndGiaAndMau(@Param(value = "tenLoai") String tenLoai,
														@Param(value = "size") String size,
														@Param(value = "minGia") int minGia,
														@Param(value = "maxGia") int maxGia,
														@Param(value = "idMau") int idMau,
														@Param(value = "n") int n);
	
	
	@Query(value = "SELECT * FROM sanpham.sanpham \r\n" + 
			"where sanpham.TenSanPham like :tenSanPham \r\n" + 
			"order by id desc\r\n" + 
			"limit 12\r\n" +
			"offset :n ;",
			nativeQuery = true)
	public List<SanPham> findSanPhamByTenSanPham(@Param(value = "tenSanPham") String tenSanPham,@Param(value = "n") int n);
	
	@Query(value = "SELECT min(sanpham.GiaTien) FROM sanpham.sanpham \r\n" + 
			";",
			nativeQuery = true)
	public int getMinGiaTien();
	
	@Query(value = "SELECT max(sanpham.GiaTien) FROM sanpham.sanpham \r\n" + 
			";",
			nativeQuery = true)
	public int getMaxGiaTien();
	
	@Query(value = "SELECT count(*) FROM sanpham.sanpham ",
			nativeQuery = true)
	public int getSoLuongSanPham();
	
	@Query(value = "SELECT DISTINCT sp.* FROM sanpham.chitietsanpham ctsp, sanpham.loaisanpham lsp,sanpham.sanpham sp, sanpham.mau m\r\n" + 
			"where sp.idLoai = lsp.id\r\n" + 
			"and sp.id = ctsp.idSanPham\r\n" + 
			"and ctsp.idMau = m.id\r\n" + 
			"and (lsp.TenLoai = :tenLoai or :tenLoai is null ) \r\n"+
			"and (m.id = :idMau or :idMau = 0 ) " + 
			"and (ctsp.Size = :size or :size is null) " +
			"and (sp.GiaTien >= :minGia or :minGia = 0) \r\n" + 
			"and (sp.GiaTien <= :maxGia or :maxGia = 0) \r\n"+
			"order by sp.id desc\r\n" + 
			"limit 12\r\n" +
			"offset :n ;",
			nativeQuery = true)
	public List<SanPham> findSanPham(@Param(value = "tenLoai") String tenLoai,
														@Param(value = "size") String size,
														@Param(value = "minGia") int minGia,
														@Param(value = "maxGia") int maxGia,
														@Param(value = "idMau") int idMau,
														@Param(value = "n") int n);
}
