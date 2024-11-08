package com.example.stronazksiazkami.book;

import com.example.stronazksiazkami.users.User;
import com.example.stronazksiazkami.users.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookServiceImpl implements BookService {
    private final BookRepository booksRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookServiceImpl(BookRepository booksReposiory, UserRepository userRepository) {
        this.booksRepository = booksReposiory;
        this.userRepository = userRepository;
    }

    public List<Book> getBooks() {
        return booksRepository.findAll();
    }

    public Book addNewBooks(Book books) {
        Optional<Book> booksOptional = booksRepository.findBooksByIsbn(books.getIsbn());
        if (booksOptional.isPresent()) {
            throw new IllegalArgumentException("isbn exists");
        }
        Book savedBook = booksRepository.save(books);
        return savedBook;
    }

    public void deleteBooks(Integer booksId, String email) {
        if (!isAdmin(email)) {
            throw new IllegalArgumentException("you are not admin");
        }
        if (!booksRepository.existsById(booksId)) {
            throw new IllegalArgumentException("books with id " + booksId + " does not exist");
        }
        booksRepository.deleteById(booksId);
    }

    @Transactional
    public void deleteBookLogically(Integer booksId) {
        Book book = booksRepository.findById(booksId).orElseThrow(() -> new IllegalArgumentException("books with id " + booksId + " does not exist"));
        book.setDeleted(true);
        booksRepository.save(book);
    }


    @Transactional
    public Book updateBooks(Integer booksId, Book updatedBook) {
        Book existingBook = booksRepository.findById(booksId)
                .orElseThrow(() -> new IllegalArgumentException("Book with id " + booksId + " does not exist"));

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setPrice(updatedBook.getPrice());
        existingBook.setLanguage(updatedBook.getLanguage());
        existingBook.setGenre(updatedBook.getGenre());
        existingBook.setPages(updatedBook.getPages());
        existingBook.setRate(updatedBook.getRate());
        existingBook.setFirstPublication(updatedBook.getFirstPublication());

        return booksRepository.save(existingBook);
    }

    private boolean isAdmin(String email) {
        Optional<User> user = userRepository.findUsersByEmail(email);
        return user.isPresent() && user.get().getIsAdmin();
    }
}