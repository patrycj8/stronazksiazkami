package com.example.stronazksiazkami.users;


import com.example.stronazksiazkami.book.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")
public class User
{
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
    @Getter @Setter @NotNull
    private Integer id;

    @Getter @Setter @NotNull
    private String login;

    @Getter @Setter @NotNull
    private String password;

    @Getter @Setter @NotNull
    private boolean isAdmin;

    @Getter @Setter @NotNull
    private String name;

    @Getter @Setter @NotNull
    private String surname;

    @Getter @Setter @NotNull
    private String phone;

    @Getter @Setter @NotNull
    private String email;

    @Getter @Setter @NotNull
    private String address;

    @Getter @Setter @NotNull
    private Integer age;

    @ManyToMany(mappedBy = "users")
    private Set<Book> books;

    @Column
    private LocalDate createdDate;

    public User()
    {

    }

    @Override
    public String toString()
    {
        return "Users{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin='" + isAdmin + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}

