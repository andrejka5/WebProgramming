package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.InMemoryBookRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import mk.ukim.finki.wp.lab.service.BookServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="BookListServlet", urlPatterns = {"/"})
public class BookListServlet extends HttpServlet {
    private BookService bookService;

    @Override
    public void init() throws ServletException {
        this.bookService = new BookServiceImpl(new InMemoryBookRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("text");
        String ratingStr = req.getParameter("rating");
        Double rating = null;
        if (ratingStr != null && !ratingStr.isEmpty()) {
            rating = Double.parseDouble(ratingStr);
        }
        List<Book> books;
        if ((text != null && !text.isEmpty()) || rating != null) {
            books = bookService.searchBooks(text != null ? text : "", rating != null ? rating : 0.0);
        } else {
            books = bookService.listAll();
        }
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='utf-8'>");
            out.println("<title>Book Reservation - Welcome and choose a Book</title>");
            out.println("<style type='text/css'>");
            out.println("body { width: 800px; margin: auto; font-family: Arial, sans-serif; }");
            out.println("h1 { color: #333; }");
            out.println("form { margin-top: 20px; }");
            out.println(".book-option { margin: 10px 0; padding: 10px; border: 1px solid #ddd; border-radius: 5px; }");
            out.println(".book-option:hover { background-color: #f5f5f5; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<header><h1>Welcome to our Book Reservation App</h1></header>");
            out.println("<form action='/' method='GET'>");
            out.println("Search by title: <input type='text' name='text' value='" + (text != null ? text : "") + "'>");
            out.println("Minimum rating: <input type='number' step='0.1' name='rating' min='0' max='5' value='" + (rating != null ? rating : "") + "'>");
            out.println("<input type='submit' value='Search'>");
            out.println("</form>");
            out.println("<main>");
            out.println("<form action='/bookReservation' method='POST'>");
            out.println("<h2>Choose a book:</h2>");
            for (Book book : books) {
                out.printf("<div class='book-option'><input type='radio' name='bookTitle' value='%s' required> ", book.getTitle());
                out.printf("Title: %s, Genre: %s, Rating: %.1f</div>%n", book.getTitle(), book.getGenre(), book.getAverageRating());
            }

            out.println("<h2>Enter your information:</h2>");
            out.println("<label>Your Name:</label><input type='text' name='readerName' required><br/>");
            out.println("<label>Your Address:</label><input type='text' name='readerAddress' required><br/>");

            out.println("<h2>Choose number of copies:</h2>");
            out.println("<input type='number' name='numCopies' min='1' max='10' required><br/><br/>");

            out.println("<input type='submit' value='Reserve Book'>");
            out.println("</form>");
            out.println("</main>");
            out.println("</body>");
            out.println("</html>");

        }
    }
}