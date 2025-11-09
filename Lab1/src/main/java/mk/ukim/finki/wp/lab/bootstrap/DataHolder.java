package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DataHolder {
    public static List<Book> books;
    public static List<BookReservation> reservations;
    @PostConstruct
    void init() {
        books = new ArrayList<>();
        reservations = new ArrayList<>();
        books.add(new Book("Harry Potter and the Deathly Hallows","Fantasy",4.62));
        books.add(new Book("Just Mercy: A Story of Justice and Redemption","Non-Fiction",4.63));
        books.add(new Book("The Way of Kings","Fantasy",4.66));
        books.add(new Book("The Nightingale","Historical Fiction",4.53));
        books.add(new Book("The Green Mile","Horror",4.43));
        books.add(new Book("Gods of the Wyrdwood","Fantasy",4.33));
        books.add(new Book("Better the Blood","Adrenaline/Thriller",4.30));
        books.add(new Book("Lady Tan's Circle of Women","Historical Fiction",4.26));
        books.add(new Book("The September House","Horror",4.21));
        books.add(new Book("A Disappearance in Fiji","Mystery",4.18));
    }
}
