package org.example.bootblogplus.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data // Lombok
@Entity // JPA
public class Article {
    // JPA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String title;
    @Column(nullable = false, length = 2000)
    String content;
    @CreatedDate
    LocalDateTime createTime = LocalDateTime.now(); // JPA
}