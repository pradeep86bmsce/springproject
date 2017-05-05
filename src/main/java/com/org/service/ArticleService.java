/**
 * 
 */
package com.org.service;

import java.util.List;

import com.org.exceptions.ServiceException;
import com.org.models.Article;

/**
 * @author M1030876
 *
 */

public interface ArticleService {

	public void addArticle(Article article) throws ServiceException;

	public void updateArticle(Article article) throws ServiceException;

	public void deleteArticle(Article article) throws ServiceException;

	public Article getArticle(int id) throws ServiceException;

	public List<Article> getArticlesByTitle(String title) throws ServiceException;

	public List<Article> getAllArticles() throws ServiceException;

	public List<Article> getArticlesByUser(String name) throws ServiceException;

}
