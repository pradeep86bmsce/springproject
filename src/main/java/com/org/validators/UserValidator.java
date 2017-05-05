/**
 * 
 */
package com.org.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.org.models.User;
import com.org.service.UserService;

/**
 * @author M1030876
 *
 */
@Component
public class UserValidator implements Validator {

	@Autowired
	UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors error) {
		User user = (User) obj;

		if (!user.getUsername().isEmpty() && (user.getUsername().length() < 6 || user.getUsername().length() > 32)) {
			error.rejectValue("username", "Size.userForm.username");
		} else if (!user.getPassword().isEmpty()
				&& (user.getPassword().length() < 8 || user.getPassword().length() > 32)) {
			error.rejectValue("password", "Size.userForm.password");
		} else if (!user.getPasswordConfirm().isEmpty() && (!user.getPasswordConfirm().equals(user.getPassword()))) {
			error.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
		} else if (userService.findByUserName(user.getUsername()) != null) {
			error.rejectValue("username", "Duplicate.userForm.username");
		}
	}

}
