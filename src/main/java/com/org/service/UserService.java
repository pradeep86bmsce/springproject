/**
 * 
 */
package com.org.service;

import com.org.exceptions.ServiceException;
import com.org.models.User;

/**
 * @author M1030876
 *
 */

public interface UserService {

	public void saveUser(User user) throws ServiceException;

	public User findByUserName(String username);
}
