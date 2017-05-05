/**
 * 
 */
package com.org.dao;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.org.exceptions.DaoException;
import com.org.models.Article;

/**
 * @author M1030876
 *
 */

@Component
public class ArticleDaoHibernateImpl extends GenericDaoHibernateImpl<Article, Integer> implements ArticleDao {

	@Override
	public void addArticle(Article article) throws DaoException {
		insert(article);
	}

	@Override
	public void updateArticle(Article article) throws DaoException {
		update(article);
	}

	@Override
	public void deleteArticle(Article article) throws DaoException {
		delete(article);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getAllArticles() throws DaoException {
		return getCriteria(Article.class, "article").addOrder(Order.desc("article.lastUpdatedDate")).list();
	}

	@Override
	public Article getArticle(int id) throws DaoException {
		return get(Article.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getArticlesByTitle(String title) throws DaoException {
		return (List<Article>) getCriteria(Article.class, "article").add(Restrictions.eq("article.title", title))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getArticlesByUser(String name) throws DaoException {
		return (List<Article>) getCriteria(Article.class, "article").createAlias("article.user", "user")
				.add(Restrictions.eq("user.username", name)).addOrder(Order.desc("article.lastUpdatedDate")).list();
	}

}
