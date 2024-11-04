package com.example.stronazksiazkami.publisher;

import com.example.stronazksiazkami.book.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Entity
@Table(name = "publishers")
public class Publisher {
    @Id
    @SequenceGenerator(
            name = "publishers_sequence",
            sequenceName = "publishers_sequense",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "publishers_sequence"
    )
    @Getter @Setter @NotNull
    private int id;

    @Getter @Setter @NotNull
    private String name;

    @Getter @Setter @NotNull
    private String phone;

    @Getter @Setter @NotNull
    private String address;

    @Getter @Setter @NotNull
    private String email;

    @Getter @Setter @NotNull
    private String website;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;

    public Publisher()
    {
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
