package com.example.stronazksiazkami.author;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService
{
    List<Author> getAuthors();
    Author addNewAuthor(Author author);
    void deleteAuthor(Integer authorId);
    Author updateAuthor(Integer authorId, Author updateAuthor);
    void deleteAuthorLogically(Integer authorId);
}
