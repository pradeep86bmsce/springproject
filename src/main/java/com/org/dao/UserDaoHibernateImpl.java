/**
 * 
 */
package com.org.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.org.exceptions.DaoException;
import com.org.models.User;

/**
 * @author M1030876
 *
 */

@Component
public class UserDaoHibernateImpl extends GenericDaoHibernateImpl<User, Integer> implements UserDao {

	@SuppressWarnings("unchecked")
	@Override
	public User findByUserName(String username) {

		List<User> users = (List<User>) currentSession().createQuery("from User where username=:username")
				.setParameter("username", username).list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void saveUser(User user) throws DaoException {
		insert(user);
	}

}
