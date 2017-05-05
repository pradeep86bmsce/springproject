/**
 * 
 */
package com.org.dao;

import java.util.List;

import com.org.exceptions.DaoException;
import com.org.models.Article;

/**
 * @author M1030876
 *
 */

public interface ArticleDao {

	public void addArticle(Article article) throws DaoException;

	public void updateArticle(Article article) throws DaoException;

	public void deleteArticle(Article article) throws DaoException;

	public Article getArticle(int id) throws DaoException;

	public List<Article> getArticlesByTitle(String title) throws DaoException;

	public List<Article> getAllArticles() throws DaoException;

	public List<Article> getArticlesByUser(String name) throws DaoException;

}
