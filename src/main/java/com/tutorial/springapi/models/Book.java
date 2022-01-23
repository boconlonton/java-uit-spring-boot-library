package com.tutorial.springapi.models;

import javax.persistence.*;

@Entity
@Table(name="tblBook")
public class Book {
    // This is "primary key"
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // AUTO-INCREMENT
    private Long id;
    // validate = constraint
    @Column(nullable = false, unique = true, length = 255)
    private String name;
    private String genre;

    public Book() {}
    // calculated field = transient
    @Transient
    private int score; // score is calculated form "name"

    public int getScore() {
        return 10;
    }

    public Book(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
