package com.website.sunbpm.NewsBlogs.Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.website.sunbpm.NewsBlogs.Entity.NewsBlogs;

@Repository
public interface NewsBlogsRepo extends JpaRepository<NewsBlogs, Long> {

    List<NewsBlogs> findByYear(String Year);

    Page<NewsBlogs> findAll(Pageable pageable);

    Page<NewsBlogs> findByCategoryContainingIgnoreCase(String newsCredit, Pageable pageable);

    List<NewsBlogs> findByLanguageCode(String languageCode);

    Page<NewsBlogs> findByCategoryContainingIgnoreCaseAndLanguageCode(String category, String languageCode, Pageable pageable);

}
