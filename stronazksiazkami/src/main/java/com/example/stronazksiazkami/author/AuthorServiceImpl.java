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
        return authorRepository.findAll();
    }

    @Override
    public Author addNewAuthor(Author author) {
        Optional<Author> existingAuthor = authorRepository.findAuthorsBySurname(author.getSurname());
        if (existingAuthor.isPresent()) {
            throw new IllegalArgumentException("Surname exists");
        }
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public void deleteAuthor(Integer authorId) {
        if (!authorRepository.existsById(authorId)) {
            throw new IllegalArgumentException("Author with id " + authorId + " does not exist");
        }
        authorRepository.deleteById(authorId);
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
