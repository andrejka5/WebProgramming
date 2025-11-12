package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import org.springframework.ui.Model;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorRepository authorRepository;

    public BookController(BookService bookService, AuthorRepository authorRepository) {
        this.bookService = bookService;
        this.authorRepository = authorRepository;
    }
    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error, Model model) {
        List<Book> books=bookService.listAll();
        model.addAttribute("books",books);
        model.addAttribute("error",error);
        return "listBooks";
    }
    @PostMapping("/add")
    public String saveBook(@RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
        Author author=authorRepository.findAll().stream().filter(a -> a.getId().equals(authorId)).findFirst().orElse(null);
        Book newBook=new Book();
        newBook.setId((long) (Math.random()*1000));
        newBook.setTitle(title);
        newBook.setGenre(genre);
        newBook.setAverageRating(averageRating);
        newBook.setAuthor(author);
        bookService.save(newBook);
        return "redirect:/books";
    }
    @PostMapping("/edit/{bookId}")
    public String editBook(@PathVariable Long bookId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
        Author author=authorRepository.findAll().stream().filter(a -> a.getId().equals(authorId)).findFirst().orElse(null);
        Book existingBook=bookService.listAll().stream().filter(b -> b.getId().equals(bookId)).findFirst().orElse(null);
        existingBook.setTitle(title);
        existingBook.setGenre(genre);
        existingBook.setAverageRating(averageRating);
        existingBook.setAuthor(author);
        bookService.update(existingBook);
        return "redirect:/books";
    }
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
    @GetMapping("/book-form/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model) {
        Optional<Book> book=bookService.findById(id);
        if(book.isPresent()) {
            model.addAttribute("book",book.get());
            model.addAttribute("authors",authorRepository.findAll());
            return "book-form";
        }
        else {
            return "redirect:/books?error=BookNotFound";
        }
    }
    @GetMapping("/book-form")
    public String getAddBookPage(Model model) {
        model.addAttribute("authors",authorRepository.findAll());
        model.addAttribute("book",null);
        return "book-form";
    }
    @PostMapping
    public String searchBooks(@RequestParam String titles,
                              @RequestParam Double ratings,
                              Model model) {
        List<Book> searchBooks=bookService.searchBooks(titles,ratings);
        model.addAttribute("searchBooks",searchBooks);
        model.addAttribute("books",bookService.listAll());
        if(searchBooks.isEmpty()) {
            model.addAttribute("hasError",true);
            model.addAttribute("error","No books found");
        }
        return "listBooks";
    }
}
