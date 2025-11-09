package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.model.Exceptions.BadArgumentsException;
import mk.ukim.finki.wp.lab.repository.InMemoryBookReservationRepository;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import mk.ukim.finki.wp.lab.service.BookReservationServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="BookReservationServlet", urlPatterns = {"/bookReservation"})
public class BookReservationServlet extends HttpServlet {
    private final BookReservationService bookReservationService;
    private final SpringTemplateEngine templateEngine;
    public BookReservationServlet(BookReservationService bookReservationService,SpringTemplateEngine templateEngine) {
        this.bookReservationService = bookReservationService;
        this.templateEngine = templateEngine;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookTitle = req.getParameter("bookTitle");
        String readerName = req.getParameter("readerName");
        String readerAddress = req.getParameter("readerAddress");
        String number=(req.getParameter("numCopies"));
        int numCopies=!number.isEmpty() ? Integer.parseInt(number) : 0;
        String clientIp = req.getRemoteAddr();
        IWebExchange webExchange= JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext webContext=new WebContext(webExchange);
        try {
            BookReservation bookReservation = this.bookReservationService.placeReservation(bookTitle, readerName, readerAddress, numCopies);
            req.setAttribute("book", bookReservation);
            webContext.setVariable("bookTitle", bookTitle);
            webContext.setVariable("readerName", readerName);
            webContext.setVariable("readerAddress", readerAddress);
            webContext.setVariable("numCopies", numCopies);
            webContext.setVariable("clientIp", clientIp);
            this.templateEngine.process("reservationConfirmation.html", webContext, resp.getWriter());
        }
        catch (BadArgumentsException e) {
            webContext.setVariable("hasError", true);
            webContext.setVariable("error", e.getMessage());
            this.templateEngine.process("reservationConfirmation.html", webContext, resp.getWriter());
        }
    }
}
