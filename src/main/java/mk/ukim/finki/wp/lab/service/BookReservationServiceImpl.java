package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.model.Exceptions.BadArgumentsException;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.repository.BookReservationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookReservationServiceImpl implements BookReservationService {
    private final List<BookReservation> reservations = new ArrayList<>();
    @Override
    public BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, int numberOfCopies) {
        if(bookTitle == null || bookTitle.isEmpty() || readerName == null || readerName.isEmpty() || readerAddress == null || readerAddress.isEmpty()) {
            throw new BadArgumentsException("Bad arguments for reserving a book");
        }
        BookReservation bookReservation = new BookReservation(bookTitle,readerName,readerAddress,numberOfCopies);
        reservations.add(bookReservation);
        return bookReservation;
    }
}
