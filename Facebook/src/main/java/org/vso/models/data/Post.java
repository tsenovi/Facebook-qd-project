package org.vso.models.data;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = false, length = 45)
    private String content;
    @Basic
    private java.time.LocalDateTime dateTime;
    @ManyToOne(optional = false,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID",referencedColumnName = "ID")
    private User author;

    protected Post(){

    }

    public Post(String content, User author) {
        this.dateTime = LocalDateTime.now();
        this.content = content;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
