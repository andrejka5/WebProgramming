package mk.ukim.finki.wp.lab.model;

import lombok.Getter;

@Getter
public class Book {
    String title;
    String genre;
    double averageRating;

    public Book(String title,String genre,double averageRating) {
        this.title = title;
        this.genre = genre;
        this.averageRating = averageRating;
    }

    public String getTitle() {
        return title;
    }

    public double getAverageRating() {
        return averageRating;
    }
    public String getGenre() {
        return genre;
    }
}
