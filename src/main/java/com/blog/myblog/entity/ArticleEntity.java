package com.blog.myblog.entity;

import com.blog.myblog.model.Article;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "article")
public class ArticleEntity {

	// FIELDS

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Size(min = 5, max = 30, message = "Wrong title size!")
	@NotEmpty(message = "Title cannot be empty!")
	private String title;

	@Size(min = 2, max = 25, message = "Wrong size of author's name!")
	@NotEmpty(message = "Author name cannot be empty!")
	private String author;

	private String image;

	@Column(name="content", columnDefinition="text")
	@NotEmpty(message = "Article content cannot be empty!")
	private String content;

	// GETTING THE MODEL

	public static Article getModel(ArticleEntity articleEntity) {
		Article article = new Article();
		article.setId(articleEntity.getId());
		article.setTitle(articleEntity.getTitle());
		article.setAuthor(articleEntity.getAuthor());
		article.setImage(articleEntity.getImage());
		article.setContent(articleEntity.getContent());
		return article;
	}

	// 	CONSTRUCTORS

	public ArticleEntity() {

	}

	public ArticleEntity(Long id, String title, String author, 
		String image, String content) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.image = image;
		this.content = content;
	}

	// GETTERS AND SETTERS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}