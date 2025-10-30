package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;
import java.util.stream.Collectors;

public class InMemoryBookRepository implements BookRepository {

    @Override
    public List<Book> findAll() {
        return DataHolder.books;
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return DataHolder.books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(text.toLowerCase())
                        && book.getAverageRating() >= rating)
                .collect(Collectors.toList());
    }
}
