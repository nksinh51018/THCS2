package com.nk.authService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nk.authService.dao.TaiKhoanDAO;
import com.nk.authService.entity.TaiKhoan;

@Service
public class TaiKhoanService {

	@Autowired
	private TaiKhoanDAO taiKhoanDAO;
	
	public List<TaiKhoan> getAllTaiKhoan(){
		Iterable<TaiKhoan> iterable =taiKhoanDAO.findAll();
		List<TaiKhoan> taiKhoans = new ArrayList<TaiKhoan>();
		iterable.forEach(taiKhoans::add);
		return taiKhoans;
	}
	
	public TaiKhoan getTaiKhoanByTenDangNhap(String tenDangNhap){
		List<TaiKhoan> taiKhoans = taiKhoanDAO.getTaiKhoanByTenDangNhap(tenDangNhap);
		if(taiKhoans.size()>0) {
			return taiKhoans.get(0);
		}
		return null;
	}
	
}
