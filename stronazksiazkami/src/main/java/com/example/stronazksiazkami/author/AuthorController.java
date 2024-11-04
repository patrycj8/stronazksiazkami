package com.example.stronazksiazkami.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }

    @PostMapping
    public ResponseEntity<Author> registerNewAuthor(@RequestBody Author author) {
        Author savedAuthor = authorService.addNewAuthor(author);
        return ResponseEntity.ok(savedAuthor);
    }

    @DeleteMapping(path = "/delete/{authorId}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("authorId") Integer authorId) {
        authorService.deleteAuthor(authorId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{authorId}")
    public ResponseEntity<Author> updateAuthor(@PathVariable("authorId") Integer authorId,
                                               @RequestBody Author updateAuthor) {
        Author updatedAuthor = authorService.updateAuthor(authorId, updateAuthor);
        return ResponseEntity.ok(updatedAuthor);
    }
}
