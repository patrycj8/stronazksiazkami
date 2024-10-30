package com.example.stronazksiazkami.author;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorsRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorsReposiory) {
        this.authorsRepository = authorsReposiory;
    }
    public List<Author> getAuthors()
    {
        return authorsRepository.findAll();
    }

   public Author addNewAuthors(Author authors) {
        Optional<Author> authorsOptional = authorsRepository.findAuthorsBySurname(authors.getSurname());
        if (authorsOptional.isPresent()) {
            throw new IllegalArgumentException("surname exists");
        }
        Author savedAuthors = authorsRepository.save(authors);
        return savedAuthors;
    }

    //fizyczne usuniecie
    public void deleteAuthorsPermanently(Integer authorsId) {
        boolean exists = authorsRepository.existsById(authorsId);
        if (!exists)
        {
            throw new IllegalArgumentException("authots with id " + authorsId + " does not exist");

        }
        authorsRepository.deleteById(authorsId);
    }
    @Transactional
    public void updateAuthorsPermanently(Integer authorsId, String name, String surname) {
        Author authors = authorsRepository.findById(authorsId).orElseThrow(() -> new IllegalArgumentException("authors with id " + authorsId + " does not exist"));
        if (name != null && name.length() >0 && !Objects.equals(authors.getName(), name)) {
            authors.setName(name);
        }
        if(surname !=null && surname.length() >0 && !Objects.equals(authors.getSurname(), surname)) {
            Optional<Author> authorsOptional = authorsRepository.findAuthorsBySurname(surname);
            if(authorsOptional.isPresent()) {
                throw new IllegalArgumentException("surname exists");
            }
            authors.setSurname(surname);

        }
    }


}
