package com.nk.hinhanhService.DTO;

import org.springframework.web.multipart.MultipartFile;

public class MyUploadForm {
	    private MultipartFile fileDatas;
	 
	    public MultipartFile getFileDatas() {
	        return fileDatas;
	    }
	 
	    public void setFileDatas(MultipartFile fileDatas) {
	        this.fileDatas = fileDatas;
	    }
}
