package com.example.stronazksiazkami.author;

import com.example.stronazksiazkami.book.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Author {
    @Id
    @SequenceGenerator(
            name = "authors_sequence",
            sequenceName = "authors_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "authors_sequence"
    )
    @NotNull
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String country;

    @NotNull
    private Integer bookCount;

    @NotNull
    private LocalDate born;

    @NotNull
    private Integer age;

    @NotNull
    private Boolean deleted = false;

    @OneToMany(mappedBy = "author")
    //@JsonManagedReference
    private List<Book> books;


    public Author() {
    }

    @Override
    public String toString() {
        return "Authors{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", bookCount=" + (books != null ? books.size() : 0) +
                ", deleted=" + deleted +
                '}';
    }
}
