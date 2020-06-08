package com.nk.hoadonService.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "hoadon")
public class HoaDon implements Serializable{
	
	@Override
	public String toString() {
		return "HoaDon [id=" + id + ", idTaiKhoan=" + idTaiKhoan + ", tenNguoiNhan=" + tenNguoiNhan + ", diaChi="
				+ diaChi + ", sdt=" + sdt + ", ngayMua=" + ngayMua + ", trangThai=" + trangThai + ", chiTietHoaDons="
				+ chiTietHoaDons + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4652592769681028790L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "idtaikhoan")
	private int idTaiKhoan;
	
	@Column(name = "tennguoinhan")
	private String tenNguoiNhan;
	
	@Column(name = "diachi")
	private String diaChi;
	
	@Column(name = "sdt")
	private String sdt;
	
	@Column(name = "ngaymua")
	private LocalDate ngayMua;
	
	@Column(name = "trangthai")
	private int trangThai;
	
	@OneToMany(mappedBy = "hoaDon",cascade = CascadeType.ALL)
	private List<ChiTietHoaDon> chiTietHoaDons;

	public List<ChiTietHoaDon> getChiTietHoaDons() {
		return chiTietHoaDons;
	}

	public void setChiTietHoaDons(List<ChiTietHoaDon> chiTietHoaDons) {
		this.chiTietHoaDons = chiTietHoaDons;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdTaiKhoan() {
		return idTaiKhoan;
	}

	public void setIdTaiKhoan(int idTaiKhoan) {
		this.idTaiKhoan = idTaiKhoan;
	}

	public String getTenNguoiNhan() {
		return tenNguoiNhan;
	}

	public void setTenNguoiNhan(String tenNguoiNhan) {
		this.tenNguoiNhan = tenNguoiNhan;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public LocalDate getNgayMua() {
		return ngayMua;
	}

	public void setNgayMua(LocalDate ngayMua) {
		this.ngayMua = ngayMua;
	}


	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public HoaDon() {
		super();
	}
	
	

}
