package com.example.demo.service;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class FileDownLoadServiceImpl {
	
	public void downloadFile(String fileNmae,
			HttpServletResponse response) {
		response.setHeader("Content-Disposition", "attachment; filename="+fileNmae);
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		try {
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			FileInputStream fis= new FileInputStream(fileNmae);
			int len;
			byte[] buf = new byte[1024];
			while( (len= fis.read(buf))>0) {
				bos.write(buf,0,len);
			}
			bos.close();
			response.flushBuffer();
			
		}catch (IOException ioException) {
			// TODO: handle exception
			ioException.printStackTrace();
		}
	}

}
