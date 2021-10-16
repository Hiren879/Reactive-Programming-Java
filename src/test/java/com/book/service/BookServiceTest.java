package com.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import reactor.test.StepVerifier;

public class BookServiceTest {

	private BookInfoService bookInfoService = new BookInfoService();
	private ReviewService reviewService = new ReviewService();
	private BookService bookService = new BookService(bookInfoService, reviewService);

	@Test
	public void getBooks() {
		var books = bookService.getBooks();
		StepVerifier.create(books)
			.assertNext(book -> {
				assertEquals("Book One", book.getBookInfo().getTitle());
				assertEquals(2, book.getBookReview().size());
			})
			.assertNext(book -> {
				assertEquals("Book Two", book.getBookInfo().getTitle());
				assertEquals(2, book.getBookReview().size());
			})
			.assertNext(book -> {
				assertEquals("Book Three", book.getBookInfo().getTitle());
				assertEquals(2, book.getBookReview().size());
			})
			.verifyComplete();
	}
	
	@Test
	public void getBookById() {
		var book = bookService.getBookById(1).log();
		StepVerifier.create(book)
			.assertNext(b -> {
				assertEquals("Book One", b.getBookInfo().getTitle());
				assertEquals(2, b.getBookReview().size());
			}).verifyComplete();
	}

}
