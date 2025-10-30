package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.InMemoryBookReservationRepository;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import mk.ukim.finki.wp.lab.service.BookReservationServiceImpl;

import java.io.IOException;

@WebServlet(name="BookReservationServlet", urlPatterns = {"/bookReservation"})
public class BookReservationServlet extends HttpServlet {
    private BookReservationService bookReservationService;
    @Override
    public void init() throws ServletException {
        this.bookReservationService = new BookReservationServiceImpl(new InMemoryBookReservationRepository());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String readerName = req.getParameter("readerName");
        String readerAddress = req.getParameter("readerAddress");
        String bookTitle = req.getParameter("bookTitle");
        int numCopies = Integer.parseInt(req.getParameter("numCopies"));
        String clientIp = req.getRemoteAddr();
        BookReservation bookReservation=bookReservationService.placeReservation(readerName,readerAddress,bookTitle,numCopies);
        req.setAttribute("readerName",readerName);
        req.setAttribute("clientIp",clientIp);
        req.setAttribute("bookTitle",bookTitle);
        req.setAttribute("numCopies",numCopies);
        req.getRequestDispatcher("/static/reservationConfirmation.html").forward(req,resp);
    }
}
