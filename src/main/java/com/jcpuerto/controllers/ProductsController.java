package com.jcpuerto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("products")
public class ProductsController {

	// private final Logger logger =
	// LoggerFactory.getLogger(ProductsController.class);
	//
	// @GetMapping()
	// public ModelAndView index(Model model) {
	//
	// logger.info("index(@ModelAttribute Person person)");
	// logger.info("model: " + model);
	//
	// ModelAndView mav = new ModelAndView();
	//
	// if (model != null) {
	//
	// // System.out.println("Products: " + person.getFirstName());
	//
	// // mav.addObject("sss", "ssssssssssssssss");
	// mav.setViewName("products");
	//
	// } else
	// mav.setViewName("home");
	//
	// return mav;
	// }
}
