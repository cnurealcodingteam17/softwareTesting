package service;

import domain.Book;
import repository.MockRepository;


public class MockService {
    private final MockRepository mockRepository;

    //이게 mock역할을 해줘야한다.
    public MockService(MockRepository mockRepository) {
        this.mockRepository = mockRepository;
    }


    public Book findByName(String name) {
        Book book = mockRepository.findByName(name);
        return book;
    }

    public int Buy_Book_change(String book_name, int money) {
        Book book = findByName(book_name);

        if (book.getPrice() > money) {
            throw new IllegalArgumentException();
        }

        return money-book.getPrice();
    }


}




