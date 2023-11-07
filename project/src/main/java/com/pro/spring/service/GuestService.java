package com.pro.spring.service;

import java.sql.SQLException;
import java.util.ArrayList;
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

	public boolean check(int post_number, String password) {
		boolean result = false;
		String pw = guestDao.check(post_number, password);
		System.out.println("postNumber : " + post_number);
		System.out.println("password : " + password);
		System.out.println(pw.equals(password));
		if (pw.equals(password)) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	public void delete(int postNumber) {
		System.out.println("service postNumber : " + postNumber);
		guestDao.delete(postNumber);
	}
}
