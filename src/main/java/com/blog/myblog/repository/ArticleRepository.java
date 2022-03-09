package com.blog.myblog.repository;

import com.blog.myblog.entity.ArticleEntity;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ArticleRepository extends CrudRepository<ArticleEntity, Long> {
	public List<ArticleEntity> findAll();
}