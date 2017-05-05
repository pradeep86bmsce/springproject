/**
 * 
 */
package com.org.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.org.dao.UserDao;
import com.org.exceptions.DaoException;
import com.org.exceptions.ServiceException;
import com.org.models.Roles;
import com.org.models.User;
import com.org.models.UserRole;

/**
 * @author M1030876
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public User findByUserName(String username) {
		return userDao.findByUserName(username);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveUser(User user) throws ServiceException {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		Set<UserRole> role = new HashSet<UserRole>();
		role.add(new UserRole(Roles.USER.toString(), user));
		user.setRoles(role);
		
		try {
			userDao.saveUser(user);
		} catch (DaoException e) {

		}
	}

}
