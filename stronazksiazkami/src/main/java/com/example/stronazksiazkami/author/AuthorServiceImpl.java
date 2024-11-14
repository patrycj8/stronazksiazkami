package com.example.stronazksiazkami.author;

import com.example.stronazksiazkami.users.User;
import com.example.stronazksiazkami.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final UserRepository userRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, UserRepository userRepository) {
        this.authorRepository = authorRepository;
        this.userRepository = userRepository;
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
    public void deleteAuthor(Integer authorId, String email) {
        if (!isAdmin(email)) {
            throw new IllegalArgumentException("You are not authorized to delete this author");
        }
        if (!authorRepository.existsById(authorId)) {
            throw new IllegalArgumentException("Author with id " + authorId + " does not exist");
        }
        authorRepository.deleteById(authorId);
    }

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

    private boolean isAdmin(String email) {
        Optional<User> user = userRepository.findUsersByEmail(email);
        return user.isPresent() && user.get().getIsAdmin();
    }
}