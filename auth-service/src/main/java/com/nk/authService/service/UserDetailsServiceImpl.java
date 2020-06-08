package com.nk.authService.service;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import org.apache.axis.encoding.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nk.authService.entity.TaiKhoan;

@Service   // It has to be annotated with @Service.
public class UserDetailsServiceImpl implements UserDetailsService  {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private TaiKhoanService taiKhoanService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// hard coding the users. All passwords must be encoded.
//		final List<AppUser> users = Arrays.asList(
//			new AppUser(1, "tubean", encoder.encode("12345"), "USER"),
//			new AppUser(2, "admin", encoder.encode("12345"), "ADMIN")
//		);
		
//		List<TaiKhoan> taiKhoans = taiKhoanService.getAllTaiKhoan();
//		
//		System.out.println("username: "+username);
//		for(TaiKhoan taiKhoan : taiKhoans) {
//			if(taiKhoan.getTenDangNhap().equals(username)) {
//				
//				// Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
//				// So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
//				List<GrantedAuthority> grantedAuthorities = AuthorityUtils
//		                	.commaSeparatedStringToAuthorityList("ROLE_" + taiKhoan.getVaiTro());
//				
//				// The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
//				// And used by auth manager to verify and check user authentication.
//				//System.out.println(appUser.getPassword());
//				return new User(taiKhoan.getTenDangNhap(),encoder.encode(taiKhoan.getMatKhau()), grantedAuthorities);
//			}
//		}
		TaiKhoan taiKhoan = taiKhoanService.getTaiKhoanByTenDangNhap(username);
		if(taiKhoan == null) {
			// If user not found. Throw this exception.
			throw new UsernameNotFoundException("Username: " + username + " not found");
		}
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
           	.commaSeparatedStringToAuthorityList("ROLE_" + taiKhoan.getVaiTro());
		try {
			return new User(taiKhoan.getTenDangNhap(),encoder.encode(decodeString(taiKhoan.getMatKhau())), grantedAuthorities);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UsernameNotFoundException("Username: " + username + " not found");
		}
//		// If user not found. Throw this exception.
//		throw new UsernameNotFoundException("Username: " + username + " not found");
		
	}
	
	public String encodeString(String text) throws UnsupportedEncodingException{
		byte[] bytes = text.getBytes("UTF-8");
		String encodeString = Base64.encode(bytes);
		return encodeString;
	}

	// Giải mã hóa một đoạn text (Đã mã hóa trước đó).
	// Decode
	public String decodeString(String encodeText) throws UnsupportedEncodingException {
		byte[] decodeBytes = Base64.decode(encodeText);
		String str = new String(decodeBytes, "UTF-8");
		return str;
	}
}