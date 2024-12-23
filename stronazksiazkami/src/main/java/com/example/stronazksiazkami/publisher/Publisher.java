
package com.example.stronazksiazkami.publisher;

import com.example.stronazksiazkami.book.Book;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;


import java.util.Set;

@Getter
@Setter
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
    @NotNull
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String phone;

    @NotNull
    private String address;

    @NotNull
    private String email;

    @NotNull
    private String website;

    @OneToMany(mappedBy = "publisher")
    @JsonIgnore
    private Set<Book> books;

    public Publisher() {
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}