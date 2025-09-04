package com.website.sunbpm.NewsBlogs.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsBlogs {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsBlogsId;

    @Column
    private String category;

    @Column
    private String title;

    @Column
    private String subtitle;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private String img1;

    @Column
    private String img2;

    @Column
    private String img3;

    @Column
    private String heroImage;

    @Column
    private String date;

    @Column
    private String year;
}
