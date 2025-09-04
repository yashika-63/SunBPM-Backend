package com.website.sunbpm.BookDemo.Repository;

import java.util.List;

import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.website.sunbpm.BookDemo.Enitity.BookDemo;

@Repository
public interface BookDemoRepo extends JpaRepository<BookDemo, Long> {
    
      List<BookDemo> findAllByOrderByCreatedAtDesc();
}
