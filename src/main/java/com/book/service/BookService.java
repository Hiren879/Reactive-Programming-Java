package com.book.service;

import java.util.List;

import com.book.model.Book;
import com.book.model.BookReview;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BookService {
	
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
		}).log();
		
	}

}
