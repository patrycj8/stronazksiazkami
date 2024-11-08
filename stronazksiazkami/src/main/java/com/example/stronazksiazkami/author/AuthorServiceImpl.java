package com.example.stronazksiazkami.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAuthors() {
        return authorRepository.findAllByDeletedFalse();
    }

    @Override
    public Author addNewAuthor(Author author) {
        System.out.println("Adding new author: " + author.getName() + " " + author.getSurname());
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    //fizyczny dla admina
    public void deleteAuthor(Integer authorId) {
        if (!authorRepository.existsById(authorId)) {
            throw new IllegalArgumentException("Author with id " + authorId + " does not exist");
        }
        authorRepository.deleteById(authorId);
    }

    //logiczny dla usera
    @Transactional
    public void deleteAuthorLogically(Integer authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new IllegalArgumentException("Author with id " + authorId + " does not exist"));
        author.setDeleted(true);
        authorRepository.save(author);
    }


    @Override
    @Transactional
    public Author updateAuthor(Integer authorId, Author updateAuthor) {
        Author existingAuthor = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author with id " + authorId + " does not exist"));

        existingAuthor.setName(updateAuthor.getName());
        existingAuthor.setSurname(updateAuthor.getSurname());
        existingAuthor.setCountry(updateAuthor.getCountry());
        existingAuthor.setBookCount(updateAuthor.getBookCount());
        existingAuthor.setBorn(updateAuthor.getBorn());
        existingAuthor.setAge(updateAuthor.getAge());

        return authorRepository.save(existingAuthor);
    }
}
