package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DownloadController {
	
	public String filePath="/home/sujay/Documents/FilesFromJavaCode/output/";
	
	@RequestMapping("/")
	public String getFileList(Model model) {
		File file= new File(filePath);
		File[] fileList=file.listFiles();
		model.addAttribute("files",fileList);
		return "showFiles";
	}

	@RequestMapping("/file/{fileName}")
	@ResponseBody
	public void downloadFile(@PathVariable("fileName") String fileNmae,
			HttpServletResponse response) {
		
		response.setHeader("Content-Disposition", "attachment; filename="+fileNmae);
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		try {
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			FileInputStream fis= new FileInputStream(filePath+fileNmae);
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
