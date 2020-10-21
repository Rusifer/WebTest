package com.webtest.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
public class UserController {
//	private List<User> users = new ArrayList<User>();
	
	@Autowired
	private UserRepository userRepository;
	
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
	public String updateForm(@PathVariable Long id, Model model) throws Exception {
		Optional<User> user =  userRepository.findById(id);
		
		if(user.isPresent()) {
			User exentity = user.get();
			model.addAttribute("user", exentity);
			return "/user/updateForm";
		} else {
			throw new Exception();
		}
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User newUser) throws Exception {
		Optional<User> user =  userRepository.findById(id);
		
		if(user.isPresent()) {
			User exentity = user.get();
			exentity.update(newUser);
			userRepository.save(exentity);
			return "redirect:/users";
		} else {
			throw new Exception();
		}
	}


}
