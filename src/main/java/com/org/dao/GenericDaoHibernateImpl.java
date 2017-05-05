/**
 * 
 */
package com.org.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.org.exceptions.DaoException;

/**
 * @author M1030876
 *
 */
public class GenericDaoHibernateImpl<T, ID extends Serializable> implements GenericDao<T, ID> {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void insert(T entity) throws DaoException {
		try {
			currentSession().save(entity);
		} catch (DataAccessException e) {
			// e.printStackTrace();
			throw new DaoException("Unable to insert entity", e);
		}
	}

	@Override
	public void update(T entity) throws DaoException {
		try {
			currentSession().update(entity);
		} catch (DataAccessException e) {
			// e.printStackTrace();
			throw new DaoException("Unable to update entity", e);
		}
	}

	@Override
	public void delete(T entity) throws DaoException {
		try {
			currentSession().delete(entity);
		} catch (DataAccessException e) {
			// e.printStackTrace();
			throw new DaoException("Unable to delete entity", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(Class<?> theClass, ID id) throws DaoException {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			return (T) session.get(theClass, id);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DaoException("Unable to get entity", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T load(Class<?> theClass, ID id) throws DaoException {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			return (T) session.load(theClass, id);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DaoException("Unable to get entity", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll(Class<?> theClass) throws DaoException {
		try {
			return currentSession().createCriteria(theClass).list();
		} catch (DataAccessException e) {
			// e.printStackTrace();
			throw new DaoException("Unable to fetch records", e);
		}
	}

	@Override
	public Criteria getCriteria(Class<?> theClass, String holder) throws DaoException {
		try {
			return currentSession().createCriteria(theClass, holder);
		} catch (DataAccessException e) {
			// e.printStackTrace();
			throw new DaoException("Unable to get Criteria", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAllByQuery(String query) throws DaoException {
		try {
			return (List<T>) currentSession().createQuery(query);
		} catch (DataAccessException e) {
			// e.printStackTrace();
			throw new DaoException("Unable to fetch records", e);
		}
	}

	public boolean deleteById(Class<?> theClass, Serializable id) {
		Object persistentInstance = currentSession().load(theClass, id);
		if (persistentInstance != null) {
			currentSession().delete(persistentInstance);
			return true;
		}
		return false;
	}
}
