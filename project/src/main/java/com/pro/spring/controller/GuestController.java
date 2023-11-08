package com.pro.spring.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pro.spring.service.GuestService;
import com.pro.spring.vo.GuestVO;

@Controller
public class GuestController {

	@Autowired
	private GuestService guestService;

	@RequestMapping("/print")
	public String print(Model model) throws SQLException {
		List<GuestVO> list = guestService.getList();
		model.addAttribute("list", list);
		return "/WEB-INF/web/print.jsp";
	}

	@RequestMapping("/board")
	public String board(@RequestParam int post_number, Model model, String id) {
		List<GuestVO> boardList = guestService.getBoardList(post_number);
		model.addAttribute("id", id);
		model.addAttribute("boardList", boardList);
		return "/WEB-INF/web/board.jsp";
	}

	@RequestMapping("/login")
	public String login() {
		return "/WEB-INF/web/login.jsp";
	}
	
	@RequestMapping("/login_check")
	public String loginCheck(@RequestParam String id, String password, Model model, HttpSession session) {
		boolean isValid = guestService.loginCheck(id, password);
		if (isValid) {
			session.setAttribute("id", id);
			model.addAttribute("userId", id);
			model.addAttribute("password", password);
			return "/print";
		} else {
			return "/WEB-INF/web/login_fail.jsp";
		}		
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("id");
		return "/print";
	}

	@RequestMapping("/insert")
	public String insert(Model model) {				
		return "/WEB-INF/web/insert.jsp";
	}

	@RequestMapping("/insertDB")
	public String insertDB(@ModelAttribute GuestVO vo) {
		// modelAttribute로 submit으로 전송한 데이터를 모두 받아온다. String값은 들어오지 못하고
		// object 타입만 들어올 수 있다. DTO의 변수명과 insert.jsp의 name과 이름을 맞춰야 된다.
		guestService.insert(vo);
		return "/print";
	}

	@RequestMapping("/insert_password")
	public String insertPassword(@RequestParam int post_number) {
		return "/WEB-INF/web/password_check.jsp";
	}

	@RequestMapping("check")
	public String check(@RequestParam int post_number, String id, String password) {
		boolean pwSame = guestService.check(id,password);
		if (pwSame) {
			return "/delete";
		} else {
			return "/WEB-INF/web/delete_fail.jsp";
		}
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam int post_number) {
		guestService.delete(post_number);
		return "/print";
	}
}
