package com.example.stronazksiazkami.author;

import jakarta.persistence.*;

import java.time.LocalDate;

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
    private int id;
    private String name;
    private String surname;
    private String country;
    private Integer bookCount;
    private LocalDate born;
    private Integer age;
    private boolean isDeleted = false;

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
