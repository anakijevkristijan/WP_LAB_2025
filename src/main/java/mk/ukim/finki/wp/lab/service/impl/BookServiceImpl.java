package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return bookRepository.findByTitleContainingIgnoreCaseAndAverageRatingGreaterThanEqual(text, rating);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(String title, String genre, double averageRating, Long authorId, Long bookId) {
        Author author = authorService.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found!"));

        Book book;
        if (bookId != null) {
            book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new RuntimeException("Book not found!"));
            book.setTitle(title);
            book.setGenre(genre);
            book.setAverageRating(averageRating);
            book.setAuthor(author);
        } else {
            book = new Book(title, genre, averageRating, author);
        }

        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
