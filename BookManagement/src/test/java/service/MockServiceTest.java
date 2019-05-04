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

    /*verify 를 사용하여 신데렐라 책을 저장하는 프로세스가 진행되었는지 테스트함.*/
    @Test
    public void bookName_Cinderella_verify(){
        Book book = mock(Book.class);

        book.setName("Cinderella");
        book.setAuthor("CharlesPerrault");
        book.setPrice(10000);

        verify(book,times(1)).setName(anyString());
    }

    /*책팔기Test 손님이 원하는 책을말하고 돈을 내면 거스름돈 주기!*/
    @Test
    public void TestChange() {

        when(mockService.findByName("WinnieThePooh")).thenReturn(new Book("WinnieThePooh", "AlanAlexanderMilne", 15000));

        assertThat(mockService.Buy_Book_change("WinnieThePooh",20000), is(5000));

    }

    /*손님이 돈을 덜 낸 경우 Test 에러 처리 될까요~?? 된다!!!!*/
    @Test(expected = IllegalArgumentException.class)
    public void exceptionTestChange(){

        when(mockService.findByName("WinnieThePooh")).thenReturn(new Book("WinnieThePooh", "AlanAlexanderMilne", 15000));
        assertThat(mockService.Buy_Book_change("WinnieThePooh",5000), is(-10000));

    }

    @Test //가격 변동
    public void Check_Changed_PriceMock(){

        when(mockService.findByName("Pinocchio")).thenReturn(new Book("Pinocchio", "CarloCollodi",12500));
        when(mockService.updatePrice_ByName("Pinocchio",20000)).thenReturn(new Book("Pinocchio", "CarloCollodi",20000));

        Book book = mockService.updatePrice_ByName("Pinocchio",20000);

        assertThat(book.getPrice(), is(20000));

    }
    //서점에서 "인어공주"라는 책을 저장하려고하면 오류를 던진다.
    //ex. "인어공주"는 판매불가 책이라고 가정.
    @Test(expected = IllegalArgumentException.class)
    public void CheckStockBook(){
        Book book = mock(Book.class);
        doThrow(new IllegalArgumentException()).when(book).setName(eq("TheLittleMermaid"));
        book.setName("TheLittleMermaid");
    }



}