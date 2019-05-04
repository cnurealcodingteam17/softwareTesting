package service;

import domain.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import repository.MockRepository;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockServiceTest{
    @Mock
    private MockRepository mockRepository;

    @InjectMocks
    private MockService mockService;

    private List<Book> books = new ArrayList<Book>();


    // test를 실행하기 전에 동작해야 리스트의 원소의 개수를 정확히 알 수 있음
    @Before
    public void setUp(){

        // 3개의 책 객체를 만듭니다.
        Book book1 = new Book("Cinderella", "CharlesPerrault", 10000);
        Book book2 = new Book("WinnieThePooh", "AlanAlexanderMilne", 15000);
        Book book3 = new Book("Pinocchio", "CarloCollodi", 12500);

        //앞서 만든 List 에 책을 추가합니다.
        books.add(book1);
        books.add(book2);
        books.add(book3);
    }


    // 책 리스트를 만들어 올바르게 저장되고 있는지 확인한다.
    @Test
    public void checkBookList(){
        assertThat(books.get(2).getPrice(), is(12500));
        assertThat(books.get(0).getName(), is("Cinderella"));
        assertThat(books.get(1).getAuthor(), is("AlanAlexanderMilne"));
    }

    // 위의 테스트와 리스트를 같이 사용하여 test 해보기
    @Test
    public void combineTwo(){
        when(mockService.findByName("Cinderella")).thenReturn(new Book("Cinderella", "AlanAlexanderMilne", 15000));
        assertThat(mockService.findByName("Cinderella").getPrice(), is(books.get(1).getPrice()));
    }



}