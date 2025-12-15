package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Component;

@Component
public class DataHolder {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataHolder(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void init() {
        if (authorRepository.count() == 0) {
            Author author1 = new Author("Neil", "Gaiman", "UK", "Fantasy and Sci-Fi writer.");
            Author author2 = new Author("Michael", "Shermer", "USA", "Science writer and skeptic.");
            Author author3 = new Author("Robert", "Greene", "USA", "Author of books on strategy, power, and seduction.");

            authorRepository.save(author1);
            authorRepository.save(author2);
            authorRepository.save(author3);

            if (bookRepository.count() == 0) {
                bookRepository.save(new Book("Neil Gaiman and Philosophy", "Philosophy", 3.5, author1));
                bookRepository.save(new Book("Between Death & Life", "Spirituality", 4.4, author2));
                bookRepository.save(new Book("If Anyone Builds It, Everyone Dies", "Artificial Intelligence", 4.2, author3));
                bookRepository.save(new Book("Manipulation and Dark Psychology", "Psychology", 3.8, author3));
                bookRepository.save(new Book("A Culture of Growth", "Economics", 3.2, author2));
                bookRepository.save(new Book("The Gifts of Athena", "Economics", 3.9, author1));
                bookRepository.save(new Book("Thank You for Leaving", "Love", 4.0, author3));
                bookRepository.save(new Book("Conform", "Fantasy", 4.1, author1));
                bookRepository.save(new Book("The Caves of Steel", "Science Fiction", 4.1, author2));
                bookRepository.save(new Book("How to Win Friends and Influence People", "Self Help", 4.4, author3));
            }
        }
    }
}
