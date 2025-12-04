//package mk.ukim.finki.wp.lab.bootstrap;
//
//import jakarta.annotation.PostConstruct;
//import mk.ukim.finki.wp.lab.model.Author;
//import mk.ukim.finki.wp.lab.model.Book;
//import mk.ukim.finki.wp.lab.model.BookReservation;
//import mk.ukim.finki.wp.lab.repository.AuthorRepository;
//import org.springframework.stereotype.Component;
//import java.util.ArrayList;
//import java.util.List;
//@Component
//public class DataHolder {
//    public static List<Book> books;
//    public static List<BookReservation> reservations;
//    private final AuthorRepository authorRepository;
//    public DataHolder(AuthorRepository authorRepository) {
//        this.authorRepository = authorRepository;
//    }
//    @PostConstruct
//    void init() {
//        List<Author> authors = authorRepository.findAll();
//        books = new ArrayList<>();
//        reservations = new ArrayList<>();
//        books.add(new Book("Harry Potter and the Deathly Hallows","Fantasy",4.62,authors.get(0)));
//        books.add(new Book("Just Mercy: A Story of Justice and Redemption","Non-Fiction",4.63,authors.get(1)));
//        books.add(new Book("The Way of Kings","Fantasy",4.66, authors.get(2)));
//        books.add(new Book("The Nightingale","Historical Fiction",4.53, authors.get(0)));
//        books.add(new Book("The Green Mile","Horror",4.43, authors.get(1)));
//        books.add(new Book("Gods of the Wyrdwood","Fantasy",4.33, authors.get(2)));
//        books.add(new Book("Better the Blood","Adrenaline/Thriller",4.30, authors.get(0)));
//        books.add(new Book("Lady Tan's Circle of Women","Historical Fiction",4.26, authors.get(1)));
//        books.add(new Book("The September House","Horror",4.21, authors.get(2)));
//        books.add(new Book("A Disappearance in Fiji","Mystery",4.18, authors.get(0)));
//    }
//}
