/**
 * 
 */
package com.org.config;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.org.dao.UserDao;
import com.org.models.Roles;
import com.org.models.User;
import com.org.models.UserRole;

/**
 * @author M1030876
 *
 */

@Component
public class AdminConfig {

	@Autowired
	private HibernateTransactionManager transactionManager;
	
	@Autowired
	private UserDao userDao;

	private String adminUsername = "admin";
	private String password = "admin";

	@PostConstruct
	private void init() {

		TransactionTemplate trxTemplate = new TransactionTemplate(transactionManager);
		trxTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				buildAdmin();
			}
		});
	}

	private void buildAdmin() {

		User admin = userDao.findByUserName(adminUsername);

		try {

			if (admin == null) {
				// create a user for the admin
				admin = new User();
				// and fill her attributes accordingly
				admin.setUsername(adminUsername);
				admin.setPassword(new BCryptPasswordEncoder().encode(password));

				Set<UserRole> role = new HashSet<UserRole>();
				role.add(new UserRole(Roles.ADMIN.toString(), admin));
				admin.setRoles(role);
				userDao.saveUser(admin);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Errors occurred during initialization. System verification is required.");
		}
	}

}
