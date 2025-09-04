package com.website.sunbpm.NewsBlogs.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.website.sunbpm.NewsBlogs.Entity.NewsBlogs;
import com.website.sunbpm.NewsBlogs.Repository.NewsBlogsRepo;
import com.website.sunbpm.NewsBlogs.Service.NewsBlogsService;

@Controller
public class NewsBlogsController {

    @Autowired
    private NewsBlogsRepo repo;

    @Autowired
    private NewsBlogsService service;

    @PostMapping("/NewsBlogsSave")
    public ResponseEntity<String> saveNewsBlogs(@RequestBody NewsBlogs NewsBlogs) {
        try {
            repo.save(NewsBlogs);
        } catch (Exception e) {
            return new ResponseEntity<String>("Not inserted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(NewsBlogs + "Successfully inserted", HttpStatus.OK);
    }

    @GetMapping("/allNewsBlogs")
    public ResponseEntity<List<NewsBlogs>> getAllNewsBlogs() {
        List<NewsBlogs> list = null;
        try {
            list = repo.findAll();
        } catch (Exception e) {
            return new ResponseEntity<List<NewsBlogs>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<NewsBlogs>>(list, HttpStatus.OK);
    }

    @GetMapping("/NewsBlogsById/{id}")
    public ResponseEntity<?> getNewsBlogsById(@PathVariable long id) {
        try {
            Optional<NewsBlogs> newsBlogs = service.getNewsBlogsById(id);
            if (newsBlogs.isPresent()) {
                return ResponseEntity.ok(newsBlogs.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("News Blogs not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occured while fetching news blogs by ID");
        }
    }

    @GetMapping("/NewsByCategory/{category}")
    public ResponseEntity<org.springframework.data.domain.Page<NewsBlogs>> getByNewsBlogsCategory(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<NewsBlogs> searchNewsBlogs = repo.findByCategoryContainingIgnoreCase(category,  PageRequest.of(page, size));
            return ResponseEntity.ok(searchNewsBlogs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/NewsBlogsUpdateById/{id}")
    public ResponseEntity<?> updateById(@PathVariable long id, @RequestBody NewsBlogs newsBlogsData) {
        try {
            NewsBlogs updatedNewsBlogs = service.updateNewsBlogs(id, newsBlogsData);
            if (updatedNewsBlogs != null) {
                return ResponseEntity.ok(updatedNewsBlogs);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("News not found for the given ID.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occured while updating News/Blogs by ID.");
        }
    }

    @DeleteMapping("/deleteNews/{id}")
    public ResponseEntity<String> deleteNewsBlogs(@PathVariable("id") long id) {
        try {
            repo.deleteById(id);
            return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}