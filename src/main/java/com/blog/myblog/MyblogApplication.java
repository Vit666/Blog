package com.blog.myblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// FILTER

import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

// MULTIPART CONFIG

import javax.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.util.unit.DataSize;

// WEB MVC CONFIGURER

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

// NL2BR

import com.github.bufferings.thymeleaf.extras.nl2br.dialect.Nl2brDialect;

@SpringBootApplication
public class MyblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyblogApplication.class, args);
	}

	// FILTER

	@Bean
    HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

    // MULTIPART CONFIG

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(128L));
        factory.setMaxRequestSize(DataSize.ofMegabytes(128L));
        return factory.createMultipartConfig();
    }

    @Bean
    public Nl2brDialect dialect() {
      return new Nl2brDialect();
    }
}
