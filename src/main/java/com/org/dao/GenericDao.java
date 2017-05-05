/**
 * 
 */
package com.org.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;

import com.org.exceptions.DaoException;

/**
 * @author M1030876
 *
 *         Generic interface for all CRUD operations
 */
public interface GenericDao<T, ID> {
	
	public void insert(T entity) throws DaoException;

	public void update(T entity) throws DaoException;

	public void delete(T entity) throws DaoException;

	public T get(Class<?> theClass, ID id) throws DaoException;

	public List<T> getAll(Class<?> theClass) throws DaoException;

	public List<T> getAllByQuery(String query) throws DaoException;

	public T load(Class<?> theClass, ID id) throws DaoException;

	public boolean deleteById(Class<?> theClass, Serializable id) throws DaoException;

	public Criteria getCriteria(Class<?> theClass, String holder) throws DaoException;
}
