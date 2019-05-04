package repository;

import domain.Book;
import java.util.List;

public interface MockRepository {
    List<Book> findAll();

    Book findByName(String book_name);

}
