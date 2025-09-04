package com.website.sunbpm.BookDemo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.website.sunbpm.BookDemo.Enitity.BookDemo;
import com.website.sunbpm.BookDemo.Repository.BookDemoRepo;

@Service
public class BookDemoService {
    
    @Autowired
    private BookDemoRepo bookDemoRepo;
    
    public BookDemo createBookDemo(BookDemo bookDemo) {
        bookDemo.setCreatedAt(LocalDateTime.now());
        return bookDemoRepo.save(bookDemo);
    }
    
    public List<BookDemo> getAllBookDemos() {
        return bookDemoRepo.findAllByOrderByCreatedAtDesc();
    }
    
    public Optional<BookDemo> getBookDemoById(Long id) {
        return bookDemoRepo.findById(id);
    }
    
    public BookDemo updateBookDemo(Long id, BookDemo bookDemo) {
        Optional<BookDemo> existingDemo = bookDemoRepo.findById(id);
        if (existingDemo.isPresent()) {
            BookDemo demo = existingDemo.get();
            demo.setFullName(bookDemo.getFullName());
            demo.setEmail(bookDemo.getEmail());
            demo.setOrganization(bookDemo.getOrganization());
            demo.setMobileNumber(bookDemo.getMobileNumber());
            demo.setProductsServices(bookDemo.getProductsServices());
            demo.setDescription(bookDemo.getDescription());
            return bookDemoRepo.save(demo);
        }
        throw new RuntimeException("BookDemo not found with id: " + id);
    }
    
    public void deleteBookDemo(Long id) {
        if (!bookDemoRepo.existsById(id)) {
            throw new RuntimeException("BookDemo not found with id: " + id);
        }
        bookDemoRepo.deleteById(id);
    }
}
