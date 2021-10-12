package com.book.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookReview {

	private long reviewId;
	private long bookId;
	private double ratings;
	private String comments;
	
}
