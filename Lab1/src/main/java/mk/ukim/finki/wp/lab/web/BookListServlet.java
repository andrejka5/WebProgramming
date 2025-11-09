package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Exceptions.BadArgumentsException;
import mk.ukim.finki.wp.lab.repository.InMemoryBookRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import mk.ukim.finki.wp.lab.service.BookServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name="BookListServlet", urlPatterns = {""})
public class BookListServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final BookService bookService;
    public BookListServlet(SpringTemplateEngine templateEngine, BookService bookService) {
        this.templateEngine = templateEngine;
        this.bookService = bookService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange= JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req,resp);
        WebContext webContext=new WebContext(webExchange);
        webContext.setVariable("books",this.bookService.listAll());
        this.templateEngine.process("listBooks.html",webContext,resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text=req.getParameter("titles");
        String rating=req.getParameter("ratings");
        Double ratingDouble= !rating.isEmpty() ? Double.parseDouble(rating) : null;
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req,resp);
        WebContext webContext=new WebContext(webExchange);
        webContext.setVariable("books",this.bookService.listAll());
        try {
            List<Book> list = bookService.searchBooks(text,ratingDouble);
            webContext.setVariable("searchBooks",list);
            this.templateEngine.process("listBooks.html",webContext,resp.getWriter());
        }
        catch (BadArgumentsException e) {
            webContext.setVariable("hasError",true);
            webContext.setVariable("error",e.getMessage());
            this.templateEngine.process("listBooks.html",webContext,resp.getWriter());
        }
    }
}