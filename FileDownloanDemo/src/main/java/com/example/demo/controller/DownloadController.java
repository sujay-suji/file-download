package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.FileDownLoadServiceImpl;

@Controller
public class DownloadController {
	
	@Autowired
	public FileDownLoadServiceImpl fileDownLoadServiceImpl;
	
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
		
		fileDownLoadServiceImpl.downloadFile(filePath+fileNmae, response);
	}
}
