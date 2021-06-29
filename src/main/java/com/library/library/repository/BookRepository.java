package com.library.library.repository;

import org.springframework.data.repository.CrudRepository;

import com.library.library.entity.Books;

public interface BookRepository extends CrudRepository<Books, Integer>{

	Books findByBookId(Integer bookId);
}
