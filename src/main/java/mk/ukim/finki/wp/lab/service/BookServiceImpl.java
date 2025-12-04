package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Exceptions.BadArgumentsException;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorServiceImpl authorServiceImpl;
    public BookServiceImpl(BookRepository bookRepository,AuthorServiceImpl authorServiceImpl) {
        this.bookRepository = bookRepository;
        this.authorServiceImpl = authorServiceImpl;
    }
    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        if(text == null || text.isEmpty() || rating == null || rating.isNaN()){
            throw new BadArgumentsException("Bad arguments for searching a book");
        }
        else {
            return bookRepository.findAll().stream()
                .filter(book -> book.getTitle().contains(text)
                        && book.getAverageRating() >= rating).toList();
        }
    }
    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }
    @Override
    public Book update(Book book) {
        if (!bookRepository.existsById(book.getId())) {
            throw new RuntimeException("Book not found: " + book.getId());
        }
        return bookRepository.save(book);
    }
    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
    @Override
    public List<Book> listByAuthor(Long authorId) {
        return bookRepository.findAllByAuthor_Id(authorId);
    }
    @Override
    public Optional<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }
}
