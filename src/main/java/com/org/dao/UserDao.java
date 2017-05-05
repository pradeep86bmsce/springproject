/**
 * 
 */
package com.org.dao;

import com.org.exceptions.DaoException;
import com.org.models.User;

/**
 * @author M1030876
 *
 */
public interface UserDao {

	public void saveUser(User user) throws DaoException;

	public User findByUserName(String username);

}
