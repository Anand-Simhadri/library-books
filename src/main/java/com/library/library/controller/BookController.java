package com.library.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.entity.Books;
import com.library.library.repository.BookRepository;

@RestController
@RequestMapping("api")
public class BookController {
	@Autowired
	private BookRepository bookRepository;

	@GetMapping("/book/searchbook/{Id}")
	public ResponseEntity<Books> getBook(@PathVariable Integer Id) {
		Books book = bookRepository.findByBookId(Id);
		if (null != book) {
			return new ResponseEntity<>(book, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(book, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/book/savebook")
	public ResponseEntity<Books> savebook(@RequestBody Books book) {
		Books book1 = bookRepository.save(book);
		if (null != book1) {
			return new ResponseEntity<>(book1, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(book1, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/books/{bookid}")
	public void deleteBook(@PathVariable Integer bookid) {
		bookRepository.deleteById(bookid);
	}

	@PutMapping("/books/{bookid}")
	public Books modifyBook(@RequestBody Books book, @PathVariable Integer bookid) { 
		return bookRepository.findById(bookid).map(b -> { b.setBookName(book.getBookName()); 
		b.setBookCategory(book.getBookCategory()); 
		b.setQuantity(book.getQuantity());
		b.setBookPrice(book.getBookPrice());
		return bookRepository.save(b); 
		})
		.orElseGet(() -> { 
		book.setBookId(bookid); 
		return bookRepository.save(book); 
		}); 
	}
}
