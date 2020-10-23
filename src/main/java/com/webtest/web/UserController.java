package com.webtest.web;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webtest.domain.User;
import com.webtest.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
//	private List<User> users = new ArrayList<User>();
	
	private static final String SESSIONED_USER = "sessionedUser";
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		
		if(user==null) {
			System.out.println("Login Success!");
			return "redirect:/users/loginForm";
		} 
		
//		if(!password.equals(user.getPassword())) {//객체에서 값을 꺼내기 보다는 객체에게 일을 시키자.
		if(!user.matchPassword(password)) {
			System.out.println("Login Success!");
			return "redirect:/users/loginForm";
		}
		
		System.out.println("Login Success!");
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
		return "redirect:/";
	}
	
	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}
	@PostMapping("")
	public String create(User user) {
		System.out.println("user: "+ user);
//		users.add(user);
		userRepository.save(user);
		return "redirect:/users";
	}
	
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		
		if(HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}
		
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		
//		if(!id.equals(sessionedUser.getId())) {
		if(!sessionedUser.matchId(id)) {
			throw new IllegalStateException(id+"자신의 정보만 수정할 수 있습니다.");
		}
		
		Optional<User> user =  userRepository.findById(id);
		
		if(user.isPresent()) {
			User exentity = user.get();
			model.addAttribute("user", exentity);
			return "/user/updateForm";
		} else {
			return "redirect:/users/list";
		}
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User updatedUser, HttpSession session) {
		
		if(HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}
		
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		
		if(!sessionedUser.matchId(id)) {
			throw new IllegalStateException(id+"자신의 정보만 수정할 수 있습니다.");
		}
		
		Optional<User> user =  userRepository.findById(id);
		
		if(user.isPresent()) {
			User exentity = user.get();
			exentity.update(updatedUser);
			userRepository.save(exentity);
			return "redirect:/users";
		} else {
			return "redirect:/users/updateForm";
		}
	}


}
