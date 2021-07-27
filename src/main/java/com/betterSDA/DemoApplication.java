package com.betterSDA;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@RestController
public class DemoApplication {

	@GetMapping("/")
	public ModelAndView displayMainPage() {
		return new ModelAndView("main");
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}
}