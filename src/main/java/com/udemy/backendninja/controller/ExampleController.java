package com.udemy.backendninja.controller;

import java.awt.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.udemy.backendninja.model.Person;


@Controller
@RequestMapping("/example")
public class ExampleController {
	
	public static final String Example_VIEW = "example";
	
	// first view
	@GetMapping("/exampleString")
	public String exampleString(Model model)
	{
		model.addAttribute("person", new Person("john", 23));
		
		return Example_VIEW;
	}
	
	@GetMapping("/exampleString2")
	public String exampleString2(Model model)
	{
		model.addAttribute("people", getPeople());
		
		return Example_VIEW;
	}
	
	
	// second view
	@GetMapping("/exampleMAV")
	public ModelAndView exampleMAV()
	{
		ModelAndView mav = new ModelAndView(Example_VIEW);
		mav.addObject("person", new Person("Jaco",25));
		return mav;
		
	}
	
	private static ArrayList<Person> getPeople(){
		ArrayList<Person> people = new ArrayList<Person>();
		people.add(new Person("John",23));
		people.add(new Person("Jose",25));
		people.add(new Person("Felipe",35));
		people.add(new Person("Anna",30));
		return people;
	};
	
	
	
	
	
}
