package com.example.stronazksiazkami.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/authors")
public class AuthorController {

    private final AuthorService authorsService;

    @Autowired
    public AuthorController(AuthorService authorsService) {
        this.authorsService = authorsService;
    }

    @GetMapping
    public List<Author> getAuthors() {
        return authorsService.getAuthors();
    }

    @PostMapping
    public ResponseEntity<Author> registerNewAuthors(@RequestBody Author authors)
    {
        Author savedAuthor = authorsService.addNewAuthors(authors);
        return ResponseEntity.ok(savedAuthor);
    }

    @DeleteMapping(path = "/delete/{authorsId}")
    public void deleteAuthors(@PathVariable("authorsId") Integer authorsId) {
        authorsService.deleteAuthorsPermanently(authorsId);
    }

    @PutMapping(path = "/{authorsId}")
    public void updateAuthors(@PathVariable("authorsId") Integer authorsId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String surname) {
        authorsService.updateAuthorsPermanently(authorsId, name, surname);
    }
}