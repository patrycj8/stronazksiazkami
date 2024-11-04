package com.example.stronazksiazkami.author;

import com.example.stronazksiazkami.book.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.List;

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
    @Getter @Setter @NotNull
    private int id;

    @Getter @Setter @NotNull
    private String name;

    @Getter @Setter @NotNull
    private String surname;

    @Getter @Setter @NotNull
    private String country;

    @Getter @Setter @NotNull
    private Integer bookCount;

    @Getter @Setter @NotNull
    private LocalDate born;

    @Getter @Setter @NotNull
    private Integer age;

    @Getter @Setter @NotNull
    private boolean isDeleted = false;

    @OneToMany(mappedBy = "author")
    private List<Book> books;


    public Author()
    {
    }

    public Author(int id,
                  String name,
                  String surname,
                  String country,
                  Integer bookCount,
                  LocalDate born,
                  Integer age,
                  boolean isDeleted)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.bookCount = bookCount;
        this.born = born;
        this.age = age;
        this.isDeleted = isDeleted;
    }

    public Author(LocalDate born,
                  Integer bookCount,
                  String country,
                  String surname,
                  String name,
                  Integer age,
                  boolean isDeleted)
    {
        this.born = born;
        this.bookCount = bookCount;
        this.country = country;
        this.surname = surname;
        this.name = name;
        this.age = age;
        this.isDeleted = isDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public LocalDate getBorn() {
        return born;
    }

    public void setBorn(LocalDate born) {
        this.born = born;
    }

    public Integer getAge() {return age;}

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isDeleted() {return isDeleted;}

    public void setDeleted(boolean isDeleted) {this.isDeleted = isDeleted;}

    @Override
    public String toString() {
        return "Authors{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", country='" + country + '\'' +
                ", bookCount=" + bookCount +
                ", bornDate=" + born +
                ", age=" + age +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
