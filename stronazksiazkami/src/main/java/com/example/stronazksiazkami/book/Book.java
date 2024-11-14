package com.example.stronazksiazkami.book;

import com.example.stronazksiazkami.author.Author;
import com.example.stronazksiazkami.publisher.Publisher;
import com.example.stronazksiazkami.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
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


    @NotNull
    private Integer id;

    @NotNull
    private String title;

    @NotNull
    private String isbn;

    @NotNull
    private Double price;

    @NotNull
    private String language;

    @NotNull
    private String genre;

    @NotNull
    private Integer rate;

    @NotNull
    private Integer pages;

    @NotNull
    private LocalDate firstPublication;

    private Boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @ManyToMany
    @JoinTable(
            name = "book_user",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    public Book() {
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", price=" + price +
                ", firstPublication=" + firstPublication +
                ", author=" + (author != null ? author.getName() + " " + author.getSurname() : "Unknown") +
                ", deleted=" + deleted +
                '}';
    }

    @JsonIgnore
    public Publisher getPublisher() {
        return publisher;
    }

}
