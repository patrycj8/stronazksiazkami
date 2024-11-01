package com.example.stronazksiazkami.author;

import java.util.List;

public interface AuthorService
{
    List<Author> getAuthors();
    Author addNewAuthors(Author authors);
    void deleteAuthorsPermanently(Integer authorsId);
    void updateAuthorsPermanently(Integer authorsId, String name, String surname);
}
