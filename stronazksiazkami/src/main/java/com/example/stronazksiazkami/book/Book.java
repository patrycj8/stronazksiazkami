package com.example.stronazksiazkami.book;

import com.example.stronazksiazkami.author.Author;
import com.example.stronazksiazkami.publisher.Publisher;
import com.example.stronazksiazkami.users.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table
public class Book {
    @Id
    @SequenceGenerator(
            name = "books_sequence",
            sequenceName = "books_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "books_sequence"
    )
    private int id;
    private String title;
    private String isbn;
    private Double price;
    private String language;
    private String genre;
    private Integer rate;
    private Integer pages;
    private LocalDate firstPublication;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    private Publisher publisher;

    @ManyToMany
    @JoinTable(
            name = "book_user",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    public Book()
    {
    }

    public Book(int id,
                String title,
                String isbn,
                Double price,
                String language,
                String genre,
                Integer rate,
                Integer pages,
                LocalDate firstPublication)
    {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.language = language;
        this.genre = genre;
        this.rate = rate;
        this.pages = pages;
        this.firstPublication = firstPublication;
    }

    public Book(LocalDate firstPublication,
                Integer pages,
                Integer rate,
                String category,
                String language,
                Double price,
                String isbn,
                String title) {
        this.firstPublication = firstPublication;
        this.pages = pages;
        this.rate = rate;
        this.genre = category;
        this.language = language;
        this.price = price;
        this.isbn = isbn;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String category) {
        this.genre = category;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public LocalDate getFirstPublication() {
        return firstPublication;
    }

    public void setFirstPublication(LocalDate firstPublication) {
        this.firstPublication = firstPublication;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn=" + isbn +
                ", price=" + price +
                ", language='" + language + '\'' +
                ", category='" + genre + '\'' +
                ", popularity=" + rate +
                ", pages=" + pages +
                ", firstPublication=" + firstPublication +
                '}';
    }
}
