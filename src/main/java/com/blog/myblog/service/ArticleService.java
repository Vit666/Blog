package com.blog.myblog.service;

import com.blog.myblog.model.Article;
import com.blog.myblog.entity.ArticleEntity;
import com.blog.myblog.repository.ArticleRepository;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ArticleService {
	
	// REPOSITORY

	private ArticleRepository articleRepository;

	@Autowired
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	// GET ARTICLES

	public List<Article> getAllArticles() {
		List<Article> articles = new ArrayList<>();
		for (ArticleEntity articleEntity : articleRepository.findAll()) {
			articles.add(ArticleEntity.getModel(articleEntity));
		}
		return articles;
	}

	public Article getSelectedArticle(Long id) {
		return ArticleEntity.getModel(articleRepository.findById(id).get());
	}

	// CREATE ARTICLES

	public void createArticle(Article article) {
		articleRepository.save(
			new ArticleEntity(
			article.getId(), 
			article.getTitle(),
			article.getAuthor(), 
			article.getImage(),
			article.getContent()));
	}

	// EDIT ARTICLES

	public void editArticle(Long id, Article article) {
		ArticleEntity articleEntity = articleRepository.findById(id).get();
		articleEntity.setId(article.getId());
		articleEntity.setTitle(article.getTitle());
		articleEntity.setAuthor(article.getAuthor());
		articleEntity.setContent(article.getContent());
		articleRepository.save(articleEntity);
	}

	public void editArticleImage(Long id, Article article) {
		ArticleEntity articleEntity = articleRepository.findById(id).get();
		articleEntity.setImage(article.getImage());
		articleRepository.save(articleEntity);
	}

	// DELETE ARTICLES

	public void deleteArticle(Long id) {
		articleRepository.delete(articleRepository.findById(id).get());
	} 
}