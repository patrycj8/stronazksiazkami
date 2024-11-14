package com.example.stronazksiazkami.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/books")
public class BookController {
    private final BookServiceImpl booksService;

    @Autowired
    public BookController(BookServiceImpl booksService) {
        this.booksService = booksService;
    }

    @GetMapping
    public List<Book> getBooks() {
        return booksService.getBooks();
    }

    @PostMapping
    public ResponseEntity<Book> registerNewBooks(@RequestBody Book books) {
        Book savedBook = booksService.addNewBooks(books);
        return ResponseEntity.ok(savedBook);
    }

    @DeleteMapping(path = "delete/{booksId}")
    public void deleteBooks(@PathVariable("booksId") Integer booksId,
                            @RequestParam("email") String email) {
        booksService.deleteBooks(booksId, email);
    }

    @DeleteMapping(path = "/deleteLogically/{bookId}")
    public ResponseEntity<Void> deleteBookLogically(@PathVariable("bookId") Integer bookId) {
        booksService.deleteBookLogically(bookId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{booksId}")
    public ResponseEntity<Book> updateBooks(@PathVariable("booksId") Integer booksId,
                                            @RequestBody Book updatedBook) {
        Book updated = booksService.updateBooks(booksId, updatedBook);
        return ResponseEntity.ok(updated);
    }


}
