package com.book.service;

import java.util.List;

import com.book.model.BookInfo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BookInfoService {

	public Flux<BookInfo> getBookInfo() {
		var books = List.of(new BookInfo(1L, "Book One", "Author One", "12121212"),
				new BookInfo(2L, "Book Two", "Author Two", "42342343"),
				new BookInfo(3L, "Book Three", "Author Three", "23425444"));

		return Flux.fromIterable(books);
	}

	public Mono<BookInfo> getBookById(long bookId) {
		var book = new BookInfo(1L, "Book One", "Author One", "12121212");
		return Mono.just(book);
	}
}
