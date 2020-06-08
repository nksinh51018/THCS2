package com.nk.hoadonService.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nk.hoadonService.dto.DanhSachChiTietSanPhamTheoDoanhThuDTO;
import com.nk.hoadonService.entity.HoaDon;


@Repository
public interface HoaDonDAO extends CrudRepository<HoaDon, Integer> {

	@Query(value = "SELECT * FROM hoadon.hoadon hd\r\n" +
			
			"where hd.idTaiKhoan = :idTaiKhoan \r\n" + 
			"order by hd.ngayMua desc\r\n"+
			"limit 12\r\n" + 
			"offset :n ;",
			nativeQuery = true)
	public List<HoaDon> getListHoaDonByIdTaiKhoan(@Param(value = "n")int n,@Param(value = "idTaiKhoan") int idTaiKhoan);
	
	@Query(value = "SELECT * FROM hoadon.hoadon hd\r\n" +
			
			"where hd.idTaiKhoan = :idTaiKhoan \r\n" +
			"and hd.id= :id \r\n;",
			
			nativeQuery = true)
	public HoaDon getHoaDonByIdTaiKhoan(@Param(value = "id")int id,@Param(value = "idTaiKhoan") int idTaiKhoan);
	
	@Query(value = "SELECT * FROM hoadon.hoadon hd\r\n" +
			"order by hd.ngayMua desc\r\n"+
			"limit 12\r\n" + 
			"offset :n ;",
			nativeQuery = true)
	public List<HoaDon> getListHoaDon(@Param(value = "n")int n);
	
	@Query(value = "SELECT * FROM hoadon.hoadon hd\r\n" +
			"WHERE ((hd.NgayMua between :ngayDatDau and :ngayKetThuc) or :ngayDatDau is null) \r\n"+
			"AND (hd.TrangThai = :trangThai or :trangThai = 3) \r\n"+
			"order by hd.ngayMua desc\r\n"+
			"limit 12\r\n" + 
			"offset :n ;",
			nativeQuery = true)
	public List<HoaDon> findHoaDon(@Param(value = "ngayDatDau") LocalDate ngayBatDau,@Param(value = "ngayKetThuc") LocalDate ngayKetThuc,@Param(value = "trangThai") int trangThai,@Param(value = "n")int n);
	
	@Query(value = "SELECT * FROM hoadon.hoadon hd\r\n" +
			"WHERE hd.id = :id ;",
			nativeQuery = true)
	public HoaDon getHoaDonById(@Param(value = "id")int id);
	
	@Query(value = "SELECT * FROM hoadon.hoadon hd\r\n" +
			
			"where hd.idTaiKhoan = :idTaiKhoan ;",
			nativeQuery = true)
	public List<HoaDon> getListHoaDonByIdTaiKhoan(@Param(value = "idTaiKhoan") int idTaiKhoan);

	@Query(value = "SELECT sum(cthd.SoLuong * cthd.Gia) FROM hoadon.hoadon hd, hoadon.chitiethoadon cthd\r\n" + 
			"where hd.id = cthd.idHoaDon\r\n" + 
			"and hd.TrangThai !=2 \r\n" +
			"and hd.NgayMua = :ngay ;",
			nativeQuery = true)
	public int getTongTienByNgay(@Param(value = "ngay") LocalDate ngay);
	
	@Query(value = "SELECT count(*) FROM hoadon.hoadon hd, hoadon.chitiethoadon cthd\r\n" + 
			"where hd.id = cthd.idHoaDon\r\n" + 
			"and hd.TrangThai !=2 \r\n" +
			"and hd.NgayMua = :ngay ;",
			nativeQuery = true)
	public int getSoLuongHoaDonByNgay(@Param(value = "ngay") LocalDate ngay);
	
	@Query(value = "SELECT sum(cthd.SoLuong * cthd.Gia) FROM hoadon.hoadon hd, hoadon.chitiethoadon cthd\r\n" + 
			"where hd.id = cthd.idHoaDon\r\n" + 
			"and hd.TrangThai !=2 \r\n" +
			"and hd.NgayMua between :ngayBatDau and :ngayKetThuc ;",
			nativeQuery = true)
	public int getTongTienByThang(@Param(value = "ngayBatDau") LocalDate ngayBatDau,@Param(value = "ngayKetThuc") LocalDate ngayKetThuc);
	
}