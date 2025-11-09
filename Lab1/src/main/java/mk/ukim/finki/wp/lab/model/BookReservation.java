package mk.ukim.finki.wp.lab.model;

import lombok.*;

@Data
@NoArgsConstructor
public class BookReservation {
    String bookTitle;
    String readerName;
    String readerAddress;
    int numberOfCopies;
    public BookReservation(String bookTitle, String readerName, String readerAddress, int numberOfCopies) {
        this.bookTitle = bookTitle;
        this.readerName = readerName;
        this.readerAddress = readerAddress;
        this.numberOfCopies = numberOfCopies;
    }
}
