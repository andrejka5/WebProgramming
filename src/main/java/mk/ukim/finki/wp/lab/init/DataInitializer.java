package mk.ukim.finki.wp.lab.init;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    public DataInitializer(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }
    @PostConstruct
    public void init() {
        Author author1 = authorRepository.save(new Author("George", "Orwell", "UK", "Author of 1984"));
        Author author2 = authorRepository.save(new Author("J.K.", "Rowling", "UK", "Author of Harry Potter"));
        Author author3 = authorRepository.save(new Author("F. Scott", "Fitzgerald", "USA", "Author of The Great Gatsby"));
        bookRepository.save(new Book("Harry Potter and the Deathly Hallows","Fantasy",4.62,author1));
        bookRepository.save(new Book("Just Mercy: A Story of Justice and Redemption","Non-Fiction",4.63,author2));
        bookRepository.save(new Book("The Way of Kings","Fantasy",4.66, author3));
        bookRepository.save(new Book("The Nightingale","Historical Fiction",4.53, author1));
        bookRepository.save(new Book("The Green Mile","Horror",4.43, author2));
        bookRepository.save(new Book("Gods of the Wyrdwood","Fantasy",4.33, author3));
        bookRepository.save(new Book("Better the Blood","Adrenaline/Thriller",4.30, author1));
        bookRepository.save(new Book("Lady Tan's Circle of Women","Historical Fiction",4.26, author2));
        bookRepository.save(new Book("The September House","Horror",4.21, author3));
        bookRepository.save(new Book("A Disappearance in Fiji","Mystery",4.18, author1));
    }
}
