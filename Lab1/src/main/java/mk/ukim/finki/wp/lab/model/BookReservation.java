package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookReservation {
    String bookTitle;
    String readerName;
    String readerAddress;
    Long numberOfCopies;
    String clientIp;

    public BookReservation(String bookTitle, String readerName, String readerAddress, long numberOfCopies) {
    }
}
