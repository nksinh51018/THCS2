package com.nk.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nk.dto.DanhSachGioHangDTO;
import com.nk.dto.GioHangDTO;

@RestController
@SessionAttributes("GioHang")
@RequestMapping("/api")
public class ApiController {

	@PostMapping("/gioHang")
	public boolean themGiohang(@RequestBody GioHangDTO gioHangDTO,HttpSession httpSession)
	{
		List<GioHangDTO> gioHangs = null;
		if(httpSession.getAttribute("GioHang")==null) {
			gioHangs = new ArrayList<GioHangDTO>();
		}
		else {
			gioHangs = (List<GioHangDTO>) httpSession.getAttribute("GioHang");
		}
		int v = viTri(gioHangDTO, gioHangs);
		if(v < 0) {
			gioHangs.add(gioHangDTO);
			httpSession.setAttribute("GioHang", gioHangs);
			return false;
		}
		else {
			gioHangs.get(v).setSoLuong(gioHangs.get(v).getSoLuong()+gioHangDTO.getSoLuong());
			httpSession.setAttribute("GioHang", gioHangs);
			return true;
		}
		
	}
	
	@DeleteMapping("/gioHang")
	public boolean xoaGiohang(@RequestBody GioHangDTO gioHangDTO,HttpSession httpSession)
	{
		System.out.println(gioHangDTO.toString());
		List<GioHangDTO> gioHangs = null;
		gioHangs = (List<GioHangDTO>) httpSession.getAttribute("GioHang");
		if(gioHangs != null) {
			int v= viTri(gioHangDTO, gioHangs);
			gioHangs.remove(v);
		}
		return true;
		
	}
	
	@GetMapping("/gioHang")
	private DanhSachGioHangDTO getAllGioHang(HttpSession httpSession) {
		List<GioHangDTO> gioHangs = (List<GioHangDTO>) httpSession.getAttribute("GioHang");
		DanhSachGioHangDTO danhSachGioHangDTO = new DanhSachGioHangDTO();
		danhSachGioHangDTO.setGioHangDTOs(gioHangs);
		return danhSachGioHangDTO;
	}
	
	private int viTri(GioHangDTO gioHangDTO,List<GioHangDTO> gioHangs) {
		int length = gioHangs.size();
		for (int i=0;i< length;i++){
			GioHangDTO gioHangDTO2 = gioHangs.get(i);
			if(gioHangDTO.getIdSanPham() == gioHangDTO2.getIdSanPham() && gioHangDTO.getIdMau() == gioHangDTO2.getIdMau() && gioHangDTO.getSize().equals(gioHangDTO2.getSize())) {
				return i;
			}
		}
		return -1;
	}
	
	@Autowired
	ServletContext context;
	
	@PostMapping("/admin/api/uploadHinhAnh")
	public String uploadfile(HttpServletRequest request) {
		final int size = 1024*1024*3;
        final int maxsize = 1024*1024*50;
        final String address = context.getRealPath("/resources/image/");
        System.out.println(address);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(!isMultipart) {
        	request.setAttribute("message", "Chua co file");
        }
        else {
        	DiskFileItemFactory factory = new DiskFileItemFactory();

        	// Set factory constraints
        	factory.setSizeThreshold(size);
        	factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        	// Create a new file upload handler
        	ServletFileUpload upload = new ServletFileUpload(factory);

        	// Set overall request size constraint
        	upload.setSizeMax(maxsize);

        	// Parse the request
        	try {
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				System.out.println(items.size());
				while (iter.hasNext()) {
				    FileItem item = iter.next();

				    if (!item.isFormField()) {
				    	String fileName = item.getName();
				    	String pathFile  = address + File.separator + fileName;
				    	File f = new File(pathFile);
				    	try {
							item.write(f);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							request.setAttribute("message", "loi viet file");
						}
				    } else {
				        
				    }
				}

			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				request.setAttribute("message", "loi upload file");
			}
        }
		return isMultipart+"";
	}
}
