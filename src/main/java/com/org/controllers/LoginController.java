/**
 * 
 */
package com.org.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.org.exceptions.ServiceException;
import com.org.models.User;
import com.org.service.UserService;
import com.org.validators.UserValidator;

/**
 * @author M1030876
 *
 */

@Controller
public class LoginController {

	@Autowired
	UserValidator userValidator;

	@Autowired
	UserService userService;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {

		model.addAttribute("userForm", new User());

		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {

		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}

		try {
			userService.saveUser(userForm);
			model.addAttribute("message", "Successfull !!");
		} catch (ServiceException e) {
			model.addAttribute("errorMsg", "Something went wrong. Please try again.");
		}

		return "registration";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(Authentication auth) {

		if (auth != null) {

		}

		return "redirect:/login?logout";
	}

}
