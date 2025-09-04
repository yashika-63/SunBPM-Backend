package com.website.sunbpm.BookDemo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.website.sunbpm.BookDemo.Enitity.BookDemo;
import com.website.sunbpm.BookDemo.Service.BookDemoService;

@RestController
@RequestMapping("/api/book-demo")
public class BookDemoController {
    
    @Autowired
    private BookDemoService bookDemoService;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createBookDemo(@RequestBody BookDemo bookDemo) {
        Map<String, Object> response = new HashMap<>();
        try {
            BookDemo savedDemo = bookDemoService.createBookDemo(bookDemo);
            response.put("success", true);
            response.put("message", "Demo request created successfully");
            response.put("data", savedDemo);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllBookDemos() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<BookDemo> bookDemos = bookDemoService.getAllBookDemos();
            response.put("success", true);
            response.put("data", bookDemos);
            response.put("count", bookDemos.size());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getBookDemoById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<BookDemo> bookDemo = bookDemoService.getBookDemoById(id);
            if (bookDemo.isPresent()) {
                response.put("success", true);
                response.put("data", bookDemo.get());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("success", false);
                response.put("message", "Demo request not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateBookDemo(@PathVariable Long id, @RequestBody BookDemo bookDemo) {
        Map<String, Object> response = new HashMap<>();
        try {
            BookDemo updatedDemo = bookDemoService.updateBookDemo(id, bookDemo);
            response.put("success", true);
            response.put("message", "Demo request updated successfully");
            response.put("data", updatedDemo);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteBookDemo(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            bookDemoService.deleteBookDemo(id);
            response.put("success", true);
            response.put("message", "Demo request deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
