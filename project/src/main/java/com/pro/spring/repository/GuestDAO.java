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
	
//	Connection con = null;
//	Statement stmt = null;
//	ResultSet rs = null;
//
//	public void connect() {
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/spring", "root", "xhddlf336!");
//			stmt = con.createStatement();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}
//
//	public void close() {
//		try {
//			stmt.close();
//			con.close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}

	public List<GuestVO> selectAll() throws SQLException {
//		List<GuestVO> list = new ArrayList<GuestVO>();
//		con = dataSource.getConnection();
		List<GuestVO> list = sqlSession.selectList("post_select_all");
//		try {
//			connect();
//			rs = stmt.executeQuery("select * from exam");
//			while (rs.next()) {
//				GuestVO guestVO = new GuestVO();
//				guestVO.setPostNumber(rs.getInt("post_number"));
//				guestVO.setName(rs.getString("name"));
//				guestVO.setPassword(rs.getString("password"));
//				guestVO.setContent(rs.getString("content"));
//				guestVO.setPostDate(rs.getString("post_date"));
//				list.add(guestVO);
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		} finally {
//			try {
//				close();
//				rs.close();
//			} catch (SQLException e) {
//				System.out.println(e);
//			}
//		}
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
//		try {
//			connect();			
//			con = dataSource.getConnection();
//			stmt = con.createStatement();
//			stmt.executeUpdate("INSERT INTO exam (name,password,content,post_date) " + "VALUES ('" + vo.getName()
//					+ "','" + vo.getPassword() + "','" + vo.getContent() + "',NOW());");
//		} catch (Exception e) {
//			System.out.println(e);
//		} finally {
//			close();
//		}
	}
	
	public String check(int post_number, String password) {		
		password = sqlSession.selectOne("select_password",post_number);
		System.out.println("dao post_number : " + post_number);
		System.out.println("dao password : " + password);
//		try {
//			connect();
//			con = dataSource.getConnection();
//			stmt = con.createStatement();
//			rs = stmt.executeQuery("select password from exam where post_number =" + postNumber + ";");
//			if (rs.next()) {
//				password = rs.getString("password");
//				System.out.println("rs.next() : " + password);
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		} finally {
//			close();
//		}
		return password;
	}

	public void delete(int postNumber) {
		System.out.println("dao postNumber : " + postNumber);
		sqlSession.delete("deletePost",postNumber);
//		try {
//			connect();			
//			con = dataSource.getConnection();
//			stmt = con.createStatement();
//			stmt.executeUpdate("delete from exam where post_number = '" + vo.getPostNumber() + "';");
//		} catch (Exception e) {
//			System.out.println(e);
//		} finally {
//			close();
//		}
	}
}
