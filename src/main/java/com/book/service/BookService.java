package com.book.service;

import java.util.List;

import com.book.exception.BookException;
import com.book.model.Book;
import com.book.model.BookReview;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class BookService {
	
	private static final String EXCEPTION_OCCURRED_WHILE_FETCHING_THE_BOOK = "Exception occurred while fetching the book :: ";
	private BookInfoService bookInfoService;
	private ReviewService reviewService;
	
	public BookService(BookInfoService bookInfoService, ReviewService reviewService) {
		this.bookInfoService = bookInfoService;
		this.reviewService = reviewService;
	}
	
	public Flux<Book> getBooks() {
		
		var allBookInfo = bookInfoService.getBookInfo();
		
		/**
		 * 1. get the flatMap
		 * 2. get bookReview and collect in list
		 * 3. create MONO of a list
		 * 4. map it and create new Book object
		 */
		return allBookInfo.flatMap(bookInfo -> {
			Mono<List<BookReview>> reviews = 
					reviewService.getBookReview(bookInfo.getBookId()).collectList();
			return reviews.map(review -> new Book(bookInfo, review));
			})
			.onErrorMap(e -> {
				log.error(EXCEPTION_OCCURRED_WHILE_FETCHING_THE_BOOK, e);
				return new BookException(EXCEPTION_OCCURRED_WHILE_FETCHING_THE_BOOK + e.getMessage());
			})
			.log();
	}
	
	public Mono<Book> getBookById(long bookId) {
		var book = bookInfoService.getBookById(bookId);
		var bookReviews = reviewService.getBookReview(bookId).collectList();
		
		return book.zipWith(bookReviews, (b,r) -> new Book(b, r));
	}

}
