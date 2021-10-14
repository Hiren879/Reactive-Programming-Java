package com.book.service;

import java.util.List;

import com.book.model.BookReview;

import reactor.core.publisher.Flux;

public class ReviewService {

	public Flux<BookReview> getBookReview(long bookId) {
		var reviewList = List.of(
				new BookReview(1, bookId, 9.1, "Good Book"),
				new BookReview(2, bookId, 8.6, "Worth Reading")
				);

		return Flux.fromIterable(reviewList);
	}

}
