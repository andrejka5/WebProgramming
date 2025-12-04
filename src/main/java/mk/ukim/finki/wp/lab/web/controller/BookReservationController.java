package mk.ukim.finki.wp.lab.web.controller;


import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bookReservation")
public class BookReservationController {
    private final BookService bookService;
    private final BookReservationService bookReservationService;
    public BookReservationController(BookService bookService, BookReservationService bookReservationService) {
        this.bookService = bookService;
        this.bookReservationService = bookReservationService;
    }
    @PostMapping
    public String makeReservation(@RequestParam String bookTitle,
                                  @RequestParam String readerName,
                                  @RequestParam String readerAddress,
                                  @RequestParam Integer numCopies,
                                  HttpServletRequest request,
                                  Model model) {
        Book book=bookService.listAll().stream().filter(b -> b.getTitle().equals(bookTitle)).findFirst().orElse(null);
        if(book==null) {
            model.addAttribute("hasError",true);
            model.addAttribute("error","Book not found");
            return "reservationConfirmation";
        }
        String clientIp=request.getRemoteAddr();
        BookReservation bookReservation=bookReservationService.placeReservation(bookTitle,readerName,readerAddress,numCopies);
        model.addAttribute("readerName",readerName);
        model.addAttribute("clientIp",clientIp);
        model.addAttribute("bookTitle",bookTitle);
        model.addAttribute("numCopies",numCopies);
        return "reservationConfirmation";
    }
}
