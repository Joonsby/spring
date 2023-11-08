package com.pro.spring.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pro.spring.vo.GuestVO;
import com.pro.spring.vo.UserInfo;

@Repository
public class GuestDAO {
	
//	@Autowired
//	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestVO> selectAll() throws SQLException {
		List<GuestVO> list = sqlSession.selectList("post_select_all");
		return list;
	}
	
	public boolean loginCheck(String id, String password) {
		Map<String, String> params = new HashMap<>();
		params.put("id",id);
		params.put("password",password);
		
		int count = sqlSession.selectOne("loginCheck",params);
		
		return count > 0;
	}
	
	public List<GuestVO> getBoardList(int post_number) {
		List<GuestVO> boardList = sqlSession.selectList("selectBoard",post_number);		
		return boardList;
	}

	public void insert(GuestVO vo) {
		sqlSession.insert("post_insert",vo);
	}
	
	public String check(String id, String password) {		
		String userPw = sqlSession.selectOne("select_password",id);
		return userPw;
	}

	public void delete(int postNumber) {		
		sqlSession.delete("deletePost",postNumber);
	}
}
