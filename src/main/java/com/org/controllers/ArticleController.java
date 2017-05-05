/**
 * 
 */
package com.org.controllers;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.org.exceptions.ServiceException;
import com.org.models.Article;
import com.org.service.ArticleService;
import com.org.service.UserService;

/**
 * @author M1030876
 *
 */

@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private UserService userService;

	@ModelAttribute("aticleStatusList")
	public Map<String, String> ArticleStatus() {
		Map<String, String> articleStatusList = new LinkedHashMap<String, String>();
		articleStatusList.put("PR", "Peer Review");
		articleStatusList.put("PL", "Published");
		articleStatusList.put("AP", "Accepted");
		articleStatusList.put("SU", "Submitted");
		return articleStatusList;
	}

	@RequestMapping(value = "/article/articleDisplay", method = RequestMethod.GET)
	public String displayPage(Article article, Model model, Authentication auth, RedirectAttributes redirectAtributes) {

		model.addAttribute("article", article == null ? new Article() : article);

		try {

			if (auth != null) {
				model.addAttribute("articles", articleService.getArticlesByUser(auth.getName()));
				model.addAttribute("userName", auth.getName());
			}
		} catch (ServiceException e) {
			model.addAttribute("errrMsg", " Not able to get articles detail : " + e.getMessage());
		}
		model.addAttribute(redirectAtributes);
		return "article";
	}

	@RequestMapping(value = "/article/addArticle/add", method = RequestMethod.GET)
	public String addArticle(@Valid Article article, BindingResult result, Authentication auth,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "article";
		}

		try {
			article.setUser(userService.findByUserName(auth.getName()));
			articleService.addArticle(article);
			redirectAttributes.addFlashAttribute("message", "Article added to the database successfully");
		} catch (ServiceException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errrMsg", " Not able to add article : " + e.getMessage());
		}

		return "redirect:/article/articleDisplay";
	}

	@RequestMapping(value = "/article/editArticle/{id}", method = RequestMethod.GET)
	public String displayArticle(@PathVariable int id, RedirectAttributes redirectAttributes) {
		try {

			redirectAttributes.addFlashAttribute(articleService.getArticle(id));

		} catch (ServiceException e) {

			redirectAttributes.addFlashAttribute("errrMsg", " Not able to load article : " + e.getMessage());
		}

		return "redirect:/article/articleDisplay";
	}

	@RequestMapping(value = "/article/addArticle/edit", method = RequestMethod.GET)
	public String editArticle(@Valid Article article, BindingResult result, Authentication auth,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "article";
		}

		try {
			article.setUser(userService.findByUserName(auth.getName()));
			articleService.updateArticle(article);
			redirectAttributes.addFlashAttribute(article);
			redirectAttributes.addFlashAttribute("message", "Article edited successfully");
		} catch (ServiceException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errrMsg", " Not able to edit article : " + e.getMessage());
		}

		return "redirect:/article/articleDisplay";
	}

	@ResponseBody
	@RequestMapping(value = "/article/deleteArticle", method = RequestMethod.POST)
	public String deleteArticle(@RequestBody Object json, RedirectAttributes redirectAttributes) {

		String rootPath = "C:/";
		try {
			File dir = new File(rootPath + File.separator + "upload");
			@SuppressWarnings("unchecked")
			Article article = articleService.getArticle(((LinkedHashMap<String, Integer>) json).get("id").intValue());

			if (null != article) {

				System.gc();

				new File(dir.getAbsolutePath() + File.separator + article.getContentName().trim()).delete();

				new File(dir.getAbsolutePath() + File.separator + article.getImageName().trim()).delete();

				articleService.deleteArticle(article);

				return "{\"message\":\"Article deleted successfully\"}";
			} else {
				return "{\"errrMsg\":\"Article not found\"}";
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errrMsg", " Not able to delete article : " + e.getMessage());
			return "{\"errrMsg\":\"Not able to delete article\"}";
		}
	}

	@RequestMapping(value = "/admin/manageArticles", method = RequestMethod.GET)
	public String adminPage(Article article, Model model, Authentication auth) {

		try {
			model.addAttribute("articles", articleService.getAllArticles());
			model.addAttribute("mode", "manage");
			model.addAttribute("userName", auth.getName());
		} catch (ServiceException e) {
			model.addAttribute("errrMsg", " Not able to get articles detail : " + e.getMessage());
		}
		return "article";

	}

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String accessDeniedPage(RedirectAttributes redirectAttributes) {

		redirectAttributes.addFlashAttribute("accessDenied", "Access denied for Page");

		return "redirect:/article/articleDisplay";
	}

	@RequestMapping(value = "/article/validateTitleUnique", method = RequestMethod.POST)
	public @ResponseBody boolean validateUsernameUnique(@RequestBody Object json, Authentication auth) {

		@SuppressWarnings("unchecked")
		String name = ((LinkedHashMap<String, String>) json).get("title");

		try {
			List<Article> articles = articleService.getArticlesByTitle(name);
			if (null != articles) {
				for (Article article : articles) {
					if (article.getUser().getUsername().equals(auth.getName())) {
						return false;
					}
				}
			}
		} catch (ServiceException e) {
			return false;
		}

		return true;
	}
}
