package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.model.Exceptions.BadArgumentsException;
import mk.ukim.finki.wp.lab.repository.InMemoryBookReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class BookReservationServiceImpl implements BookReservationService {
    private final InMemoryBookReservationRepository reservationRepository;
    public BookReservationServiceImpl(InMemoryBookReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
    @Override
    public BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, int numberOfCopies) {
        if(bookTitle == null || bookTitle.isEmpty() || readerName == null || readerName.isEmpty() || readerAddress == null || readerAddress.isEmpty()) {
            throw new BadArgumentsException("Bad arguments for reserving a book");
        }
        BookReservation bookReservation = new BookReservation(bookTitle,readerName,readerAddress,numberOfCopies);
        return this.reservationRepository.save(bookReservation);
    }
}
