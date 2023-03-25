package net.javaguides.springboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.entity.Book;
import net.javaguides.springboot.repository.BookRepository;

@Repository
public class BookDao {
	
	
	@Autowired
	BookRepository  bookRepository;
	
	
	public Book saveBook(Book book)
	{
		return bookRepository.save(book);
	}
	
	
	public List<Book> findBook(String id)
	{
		return bookRepository.findByStudent(id);
	}


	public Book findBookByRegdId(String id) {
		return bookRepository.findByRegdId(id);
	}


	public Book findBookByBookId(String id) {
		return bookRepository.findByBookId(id);
	}
	
	public void deleteByBookId(String bookId)
	{
		bookRepository.deleteById(bookId);
	}
	
	public void deleteById(String std) {
		List<Book> list=bookRepository.findByStudent(std);
		for (Book book : list) {
			bookRepository.deleteById(book.getBookId());
		}
	}

}
