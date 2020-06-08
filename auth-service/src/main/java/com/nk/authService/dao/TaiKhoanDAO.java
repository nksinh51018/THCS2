package com.nk.authService.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nk.authService.entity.TaiKhoan;

public interface TaiKhoanDAO extends CrudRepository<TaiKhoan, Integer>{

	public List<TaiKhoan> getTaiKhoanByTenDangNhap(String tenDangNhap);
}
