package com.website.sunbpm.NewsBlogs.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.sunbpm.NewsBlogs.Entity.NewsBlogs;
import com.website.sunbpm.NewsBlogs.Repository.NewsBlogsRepo;

@Service
public class NewsBlogsService {

    @Autowired
    private NewsBlogsRepo repo;

    public Optional<NewsBlogs> getNewsBlogsById(long id) {
        return repo.findById(id);
    }

    public NewsBlogs updateNewsBlogs(long newsBlogsId, NewsBlogs newsBlogsData) {
        Optional<NewsBlogs> optionalNews = repo.findById(newsBlogsId);
        if (optionalNews.isPresent()) {
            NewsBlogs existingNewsBlogs = optionalNews.get();
            existingNewsBlogs.setCategory(newsBlogsData.getCategory());
            existingNewsBlogs.setTitle(newsBlogsData.getTitle());
            existingNewsBlogs.setSubtitle(newsBlogsData.getSubtitle());
            existingNewsBlogs.setDescription(newsBlogsData.getDescription());
            existingNewsBlogs.setImg1(newsBlogsData.getImg1());
            existingNewsBlogs.setImg2(newsBlogsData.getImg2());
            existingNewsBlogs.setImg3(newsBlogsData.getImg3());
            existingNewsBlogs.setHeroImage(newsBlogsData.getHeroImage());
            existingNewsBlogs.setDate(newsBlogsData.getDate());
            existingNewsBlogs.setYear(newsBlogsData.getYear());
            return repo.save(existingNewsBlogs);
        } else {
            return null;
        }
    }
}