package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping({"/", "/books"})
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error,
                               @RequestParam(required = false) String text,
                               @RequestParam(required = false) Double rating,
                               Model model) {
        List<Book> books;
        if (text != null && !text.isBlank() && rating != null) {
            books = bookService.searchBooks(text, rating);
        } else {
            books = bookService.listAll();
        }

        model.addAttribute("books", books);
        model.addAttribute("text", text);
        model.addAttribute("rating", rating);
        model.addAttribute("error", error);

        return "listBooks";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
        bookService.save(title, genre, averageRating, authorId, null);
        return "redirect:/books";
    }

    @PostMapping("/edit/{bookId}")
    public String editBook(@PathVariable Long bookId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
        bookService.save(title, genre, averageRating, authorId, bookId);
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/book-form")
    public String getAddBookPage(Model model) {
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("book", null);
        return "book-form";
    }

    @GetMapping("/book-form/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return bookService.findById(id)
                .map(book -> {
                    model.addAttribute("book", book);
                    model.addAttribute("authors", authorService.findAll());
                    return "book-form";
                })
                .orElseGet(() -> {
                    redirectAttributes.addAttribute("error", "BookNotFound");
                    return "redirect:/books";
                });
    }
}
