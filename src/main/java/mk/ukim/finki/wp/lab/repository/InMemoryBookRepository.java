//package mk.ukim.finki.wp.lab.repository;
//
//import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
//import mk.ukim.finki.wp.lab.model.Book;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//@Repository
//public class InMemoryBookRepository implements BookRepository {
//
//    @Override
//    public List<Book> findAll() {
//        return DataHolder.books;
//    }
//
//    @Override
//    public List<Book> searchBooks(String text, Double rating) {
//        return DataHolder.books.stream()
//                .filter(book -> book.getTitle().contains(text)
//                        && book.getAverageRating() >= rating).toList();
//    }
//    @Override
//    public Book save(Book book) {
//        DataHolder.books.add(book);
//        return book;
//    }
//    @Override
//    public Book update(Book book) {
//        for(int i=0;i<DataHolder.books.size();i++) {
//            if(DataHolder.books.get(i).getId().equals(book.getId())) {
//                DataHolder.books.set(i, book);
//                return book;
//            }
//        }
//        return null;
//    }
//    @Override
//    public void delete(Long id) {
//        DataHolder.books.removeIf(book -> book.getId().equals(id));
//    }
//}
