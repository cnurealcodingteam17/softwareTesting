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


}




