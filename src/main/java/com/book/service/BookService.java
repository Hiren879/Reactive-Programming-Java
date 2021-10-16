package com.book.service;

import java.time.Duration;
import java.util.List;

import com.book.exception.BookException;
import com.book.model.Book;
import com.book.model.BookReview;

import lombok.extern.slf4j.Slf4j;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

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
	
	public Flux<Book> getBooksRetry() {
		
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
			.retry(3)
			.log();
	}
	
	/**
	 * Important method.
	 * 1. retry mechanism
	 * 2. retry with filter
	 * 3. flatMap
	 * @return
	 */
	public Flux<Book> getBooksRetryWhen() {
	
		// Specify how many time do you want to retry and at what interval
		// This is very useful feature in MicorService environment
		var retrySpec = getRetryBackOffSpec(); 
	
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
			.retryWhen(retrySpec)
			.log();
	}

	private RetryBackoffSpec getRetryBackOffSpec() {
		return Retry.backoff(3, Duration.ofMillis(1000))
						.filter(throwable -> throwable instanceof BookException) // retry only when exception of type BookException
						.onRetryExhaustedThrow((retryBackOffSpec, retrySignal) // catch the exhausted exception 
								-> Exceptions.propagate(retrySignal.failure())); // propagate it
	}
	
	public Mono<Book> getBookById(long bookId) {
		var book = bookInfoService.getBookById(bookId);
		var bookReviews = reviewService.getBookReview(bookId).collectList();
		
		return book.zipWith(bookReviews, (b,r) -> new Book(b, r));
	}

}
