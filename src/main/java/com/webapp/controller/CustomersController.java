package com.webapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.webapp.model.Customers;
import com.webapp.service.CustomersService;

@Controller
public class CustomersController {
	@Autowired
	private CustomersService customerSer;

	@GetMapping({ "/showCustomers", "/", "/list" })
	public ModelAndView showAllCustomers() {
		ModelAndView mav = new ModelAndView("list-Customers");
		List<Customers> list = customerSer.getAllCustomers();
		mav.addObject("customers", list);
		return mav;
	}

	// Handler that get the form for user input
	@GetMapping("/addCustomersForm")
	public ModelAndView AddCustomersForm() {
		ModelAndView mav = new ModelAndView("add-customer-form");
		// Customers c = new Customers();
		mav.addObject("customers", new Customers());
		return mav;
	}

	// Handler that save the Customer using the service class.
	// after saving the Handler will redirect to the home page to see the list
	//Using Validation for the form to add Customer @valid and BindingResult
	@PostMapping("/saveCustomer")
	public String saveCustomer(@Valid @ModelAttribute Customers customer,BindingResult bindingResult)
	{
	    if(bindingResult.hasErrors()) {
	    	return "add-customer-form";
	    }
	    else {
		customerSer.saveCustomer(customer);
		return "redirect:/list";
	    }
	}

	@GetMapping("/showUpdateForm")
	public ModelAndView showUpdateForm(@RequestParam Long customerId) {
		ModelAndView mav = new ModelAndView("add-customer-form");
		mav.addObject("customers", customerSer.getCustomerById(customerId));
		return mav;
	}
	

	@GetMapping("/customerDelete")
	public String delteCustomer(@RequestParam Long customerId) {
		customerSer.deleteCustomerById(customerId);
		return "redirect:/list";
	}

	@GetMapping("/searchCustomersForm")
	public String searchCustomerForm(Model model) {
		model.addAttribute(new Customers());
		return "search-customer";
	}
	/*
	 * @GetMapping("/searchCustomersForm") public ModelAndView searchCustomerForm()
	 * { ModelAndView mav = new ModelAndView("search-customer"); Customers c = new
	 * Customers(); mav.addObject("customers",new Customers()); return mav; }*
	 * 
	 * /*
	 * 
	 * @PostMapping("/searchCustomers") public ModelAndView
	 * searchCustomer(@RequestParam Long customerId) { ModelAndView mav = new
	 * ModelAndView("search-customer"); mav.addObject("customers",
	 * customerSer.getCustomerById(customerId)); return mav; }
	 */

	@PostMapping("/search")
	public String searchCustomer(@ModelAttribute("virgo") Customers c, BindingResult bindingResult, Model model) {

		//Verifying the result if null return the page whit no value else perform the search
        if(customerSer.getCustomerById(c.getId()) == null){
        	return "redirect:/searchCustomersForm";
         }
        else {
		model.addAttribute("customers", customerSer.getCustomerById(c.getId()));
		return "search-customer";
        }
	}
	/*
	 * @PostMapping("/search") public ModelAndView searchCustomer(@RequestParam Long
	 * customerId) { System.out.println(customerId); customerId =(long) 1;
	 * ModelAndView mav = new ModelAndView("search-customer");
	 * mav.addObject("customers", customerSer.getCustomerById(customerId)); return
	 * mav; }
	 */

	/*
	 * @RequestMapping("/search/{id}") public ModelAndView
	 * searchCustomer(@PathVariable(name="id") long id) { ModelAndView mav = new
	 * ModelAndView("search-customer"); mav.addObject("customers",
	 * customerSer.getCustomerById(id)); return mav;
	 * 
	 * 
	 * }
	 */
}
