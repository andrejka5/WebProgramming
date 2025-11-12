package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Exceptions.BadArgumentsException;
import mk.ukim.finki.wp.lab.repository.InMemoryBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final InMemoryBookRepository bookRepository;
    public BookServiceImpl(InMemoryBookRepository bookRepository) {
        this.bookRepository = bookRepository;
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
            return bookRepository.searchBooks(text, rating);
        }
    }
    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }
    @Override
    public Book update(Book book) {
        return bookRepository.update(book);
    }
    @Override
    public void delete(Long id) {
        bookRepository.delete(id);
    }
    @Override
    public Optional<Book> findById(Long id) {
        return DataHolder.books.stream().filter(b -> b.getId().equals(id)).findFirst();
    }
}
