package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book {
    String title;
    String genre;
    double averageRating;
    public Book(String title, String genre, double averageRating) {
        this.title = title;
        this.genre = genre;
        this.averageRating = averageRating;
    }
    public String getTitle() {
        return title;
    }
    public String getGenre() {
        return genre;
    }
    public double getAverageRating() {
        return averageRating;
    }
}
