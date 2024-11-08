package com.example.stronazksiazkami.users;

import com.example.stronazksiazkami.book.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )

    private Integer id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String address;
    private Integer age;


    @ManyToMany(mappedBy = "users")
    private Set<Book> books;

    @Column
    private LocalDate createdDate;
    private Boolean isAdmin = false;
    private Boolean deleted = false;

    public User() {

    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin='" + isAdmin + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                '}';
    }
}

