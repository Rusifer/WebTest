package com.webtest.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	@GetMapping("/helloworld")
	public String welcome(Model model) {
		
//		List<TestModel> repoList = new ArrayList<TestModel>();
		List<TestModel> repo = Arrays.asList(new TestModel("test"), new TestModel("test2"));
		/*
		 * model.addAttribute("name", "자바머스태쉬"); model.addAttribute("age", 10);
		 * model.addAttribute("company", "<b>GitHub</b>"); model.addAttribute("company",
		 * "<b>GitHub</b>");
		 */
		model.addAttribute("repo", repo);
		 
		return "welcome";
	}
}
