package com.book.service.mock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.book.exception.BookException;
import com.book.service.BookInfoService;
import com.book.service.BookService;
import com.book.service.ReviewService;

import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class BookServiceMockTest {

	@Mock
	private BookInfoService bookInfoService;

	@Mock
	private ReviewService reviewService;

	@InjectMocks
	private BookService bookService;

	@Test
	void getBooksMock() {

		Mockito.when(bookInfoService.getBookInfo()).thenCallRealMethod();

		Mockito.when(reviewService.getBookReview(Mockito.anyLong())).thenCallRealMethod();

		var books = bookService.getBooks();

		StepVerifier.create(books).expectNextCount(3).verifyComplete();

	}

	@Test
	void getBooksMockException() {

		Mockito.when(bookInfoService.getBookInfo()).thenCallRealMethod();

		Mockito.when(reviewService.getBookReview(Mockito.anyLong()))
				.thenThrow(new IllegalStateException("Exception from Mock test"));

		var books = bookService.getBooks();

		StepVerifier.create(books).expectError(BookException.class).verify();

	}
	
	@Test
	void getBooksRetryTest() {
		Mockito.when(bookInfoService.getBookInfo()).thenCallRealMethod();

		Mockito.when(reviewService.getBookReview(Mockito.anyLong()))
				.thenThrow(new IllegalStateException("Exception from Mock test"));

		var books = bookService.getBooksRetry();

		StepVerifier.create(books).expectError(BookException.class).verify();
	}
	
	@Test
	void getBooksRetryWhenTest() {
		Mockito.when(bookInfoService.getBookInfo()).thenCallRealMethod();

		Mockito.when(reviewService.getBookReview(Mockito.anyLong()))
				.thenThrow(new IllegalStateException("Exception from Mock test"));

		var books = bookService.getBooksRetryWhen();

		StepVerifier.create(books).expectError(BookException.class).verify();
	}
}
