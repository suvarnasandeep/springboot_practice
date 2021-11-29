package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {

	@Autowired
	CustomerRepo repo;
	@RequestMapping("/")
	public String customer() {
		return "myjsp";
	}

	@RequestMapping("/details")
	public String details(Customers customers) {

		repo.save(customers);
		return "myjsp";
	}

	@RequestMapping("/getdetails")
	public String getdetails() {

		return "viewCustomer";
	}


	@PostMapping("/getdetails") 
	public ModelAndView getDetails(@RequestParam int cid) {
		
		ModelAndView mv = new ModelAndView("Retrive");		
		Customers customers = repo.findById(cid).orElse(null);		
		mv.addObject(customers);
		return mv;
	}

	@RequestMapping("/customers")
	@ResponseBody
	public java.util.List<Customers> getCustomers() {
		return repo.findAll();
	}
	
	@RequestMapping("/customers/{cid}")
	@ResponseBody
	public String getCustomersId(@PathVariable("cid")int  cid) {
		return repo.findById(cid).toString();
	}
	
	@PostMapping("/customers")
		public Customers getCustomersId2(@RequestBody Customers customers) {
		repo.save(customers);
		return customers;
		
	}
	
	@DeleteMapping("/customers/{cid}")
	public Customers getCustomersId3(@PathVariable("cid") int cid) {
	
		Customers cust = repo.getOne(cid);
		repo.delete(cust);
		return cust;
	}
	
	@PutMapping(path="/customers", consumes = {"application/json"})
	public Customers getCustomersId4(@RequestBody Customers customers) {
	
		repo.save(customers);
		return customers;	
	}
	
}
