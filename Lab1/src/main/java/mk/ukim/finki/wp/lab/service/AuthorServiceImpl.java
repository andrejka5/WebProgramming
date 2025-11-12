package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
