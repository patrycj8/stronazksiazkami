package com.example.stronazksiazkami.book;

import com.example.stronazksiazkami.author.Author;
import com.example.stronazksiazkami.publisher.Publisher;
import com.example.stronazksiazkami.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

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
    @Getter @Setter @NotNull
    private int id;

    @Getter @Setter @NotNull
    private String title;

    @Getter @Setter @NotNull
    private String isbn;

    @Getter @Setter @NotNull
    private Double price;

    @Getter @Setter @NotNull
    private String language;

    @Getter @Setter @NotNull
    private String genre;

    @Getter @Setter @NotNull
    private Integer rate;

    @Getter @Setter @NotNull
    private Integer pages;

    @Getter @Setter @NotNull
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
