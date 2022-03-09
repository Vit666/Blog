package com.blog.myblog.controller;

import com.blog.myblog.model.Article;
import com.blog.myblog.service.ArticleService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;

import java.util.Random;

@Controller
public class ArticleController {

	private static final String IMAGESPATH = "target/classes/static/blog/images/";

	// SERVICE

	private ArticleService articleService;

	@Autowired
	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	// READ ARTICLES

	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("articles", articleService.getAllArticles());
		return "articles/home";
	}

	@GetMapping("/article/{id}")
	public String article(@PathVariable("id") Long id, Model model) {
		model.addAttribute("article", articleService.getSelectedArticle(id));
		return "articles/article";
	}

	// CREATE ARTICLES

	@GetMapping("/add")
	public String add(@ModelAttribute("article") Article article) {
		return "articles/add";
	}

	@PostMapping("/add")
	public String createArticle(
		@ModelAttribute("article") @Valid Article article,
		Model model,
		BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "articles/add";
		}

		articleService.createArticle(article);
		return "redirect:/home";
	}

	// EDIT ARTICLES

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("article", articleService.getSelectedArticle(id));
		return "articles/edit";
	}

	@PatchMapping("/edit/{id}")
	public String editArticle(@PathVariable("id") Long id,
		@ModelAttribute("article") @Valid Article article,
		BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "articles/edit";
		}
		articleService.editArticle(id, article);
		return "redirect:/home";
	}

	@PatchMapping("/editImage/{id}")
	public String editArticleImage(@PathVariable("id") Long id,
		@ModelAttribute("article") @Valid Article article,
		BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "articles/edit";
		}
		articleService.editArticleImage(id, article);
		return "redirect:/home";
	}

	// UPLOAD NEW IMAGE

    @PostMapping("/uploadImage")
    public String uploadImage(@ModelAttribute("image") MultipartFile image,
    	Model model) {
    	Random random = new Random();

        if (!image.isEmpty()) {
			try (BufferedOutputStream stream = new BufferedOutputStream(
				new FileOutputStream(
					new File(IMAGESPATH + "image" + random.nextInt() + ".jpg")))) {

				byte[] bytes = image.getBytes();
				stream.write(bytes);

			} catch (Exception e) {
				model.addAttribute("message", e.toString());
				return "articles/failed";
			}
		} else {
			model.addAttribute("message", "Image was not uploaded! File is empty!");
			return "articles/failed";
		}

		return "redirect:/add";
    }

	// DELETE ARTICLES

	@DeleteMapping("/delete/{id}")
	public String deleteArticle(@PathVariable("id") Long id) {
		articleService.deleteArticle(id);
		return "redirect:/home";
	}
}