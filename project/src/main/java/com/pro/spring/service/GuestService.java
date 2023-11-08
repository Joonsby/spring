package com.pro.spring.service;

import java.io.File;
import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public void insert(GuestVO vo) {		
		guestDao.insert(vo);
	}

	public boolean check(String id,String password) {				
		boolean result = false;
		String pw = guestDao.check(id,password);
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
}
