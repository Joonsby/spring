package com.pro.spring.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.pro.spring.repository.GuestDAO;
import com.pro.spring.vo.GuestVO;

@Service
public class GuestService {
	@Autowired
	private GuestDAO guestDao;

	public List<GuestVO> getList() throws SQLException {
		List<GuestVO> list = guestDao.selectAll();
		return list;
	}

	public boolean loginCheck(String id, String password) {
		boolean isValid = guestDao.loginCheck(id, password);
		return isValid;
	}

	public List<GuestVO> getBoardList(int post_number) {
		List<GuestVO> boardList = guestDao.getBoardList(post_number);
		return boardList;
	}

	public void insert(GuestVO vo, HttpServletRequest req) {
		String uploadPath = req.getRealPath("/upload");
		int size = 10 * 1024 * 1024;
		String id = "";		
		String title = "";
		String content = "";
		String filename = "";
		String origFilename1 = "";
		
		try {
			MultipartRequest multi = new MultipartRequest(req, uploadPath, size, "UTF-8",new DefaultFileRenamePolicy());			
			
			Enumeration files = multi.getFileNames();
			
			String file = (String) files.nextElement();
			filename = multi.getFilesystemName(file);
			origFilename1 = multi.getOriginalFileName(file);
			
			vo.setId(multi.getParameter("id"));
			vo.setTitle(multi.getParameter("title"));
			vo.setContent(multi.getParameter("content"));	
			vo.setFile(filename);			

			guestDao.insert(vo);
			
		} catch(Exception e) {
			System.out.println(e);
		}		
	}

	public boolean check(String id, String password) {
		boolean result = false;
		String pw = guestDao.check(id, password);
		if (pw.equals(password)) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	public void delete(int postNumber) {
		guestDao.delete(postNumber);
	}
	
	public void download(HttpServletRequest req, HttpServletResponse res) {
		String fileName = req.getParameter("file");
		String savePath = "/upload";		
		ServletContext context = req.getServletContext();
		String sDownloadPath = context.getRealPath(savePath);
		String sFilePath = sDownloadPath + "\\" + fileName;	
		byte b[] = new byte[4096];
		try {
			FileInputStream in = new FileInputStream(sFilePath);
			String sMimeType = req.getServletContext().getMimeType(sFilePath);
			System.out.println("sMimeType>>>" + sMimeType);
			
			if (sMimeType == null) {
				sMimeType = "application/octet-stream";
			}
			
			res.setContentType(sMimeType);
			String agent = req.getHeader("User-Agent");
			boolean ieBrowser = (agent.indexOf("MSIE") > -1) || (agent.indexOf("Trident") > -1);

			if (ieBrowser) {
				fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
			} else {
				fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
			}
			res.setHeader("Content-Disposition", "attachment; filename= " + fileName);
			// 파일 이름	

			ServletOutputStream out2 = res.getOutputStream();
			int numRead;

			while ((numRead = in.read(b, 0, b.length)) != -1) {
				out2.write(b, 0, numRead);
			}
			out2.flush();
			out2.close();
			in.close();
			System.out.println("complete");
		} catch(Exception e) {
			System.out.println(e);
		}			
	}
}
