package mk.ukim.finki.wp.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class AuthorRepository {
    List<Author> authors=new ArrayList<>();
    @PostConstruct
    public void init(){
        authors.add(new Author("George", "Orwell", "UK", "Author of 1984 and Animal Farm"));
        authors.add(new Author("J.K.", "Rowling", "UK", "Author of the Harry Potter series"));
        authors.add(new Author("F. Scott", "Fitzgerald", "USA", "Author of The Great Gatsby"));
    }
    public List<Author> findAll(){
        return authors;
    }
}
