package com.org.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.org.dao.ArticleDao;
import com.org.exceptions.DaoException;
import com.org.exceptions.ServiceException;
import com.org.models.Article;

/**
 * @author M1030876
 *
 */

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addArticle(Article article) throws ServiceException {
		try {
			articleDao.addArticle(article);
		} catch (DaoException e) {
			throw new ServiceException("Adding article failed", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateArticle(Article article) throws ServiceException {
		try {
			articleDao.updateArticle(article);
		} catch (DaoException e) {
			throw new ServiceException("Updating article failed", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteArticle(Article article) throws ServiceException {
		try {
			articleDao.deleteArticle(article);
		} catch (DaoException e) {
			throw new ServiceException("Deleting article failed", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Article> getAllArticles() throws ServiceException {
		try {
			return articleDao.getAllArticles();
		} catch (DaoException e) {
			throw new ServiceException("Getting all articles failed", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Article getArticle(int id) throws ServiceException {
		try {
			return articleDao.getArticle(id);
		} catch (DaoException e) {
			throw new ServiceException("Getting article failed", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Article> getArticlesByTitle(String title) throws ServiceException {
		try {
			return articleDao.getArticlesByTitle(title);
		} catch (DaoException e) {
			throw new ServiceException("Getting article failed", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Article> getArticlesByUser(String name) throws ServiceException {
		try {
			return articleDao.getArticlesByUser(name);
		} catch (DaoException e) {
			throw new ServiceException("Getting articles failed", e);
		}
	}

}
