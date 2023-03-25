package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.dao.BookDao;
import net.javaguides.springboot.entity.Book;

@Service
public class BookService {

	@Autowired
	BookDao bookDao;

	public Book saveBook(Book book) {
		return bookDao.saveBook(book);
	}

	public List<Book> findBook(String id) {
		return bookDao.findBook(id);
	}

	public Book findBookByRegdId(String id) {
		return bookDao.findBookByRegdId(id);
	}

	public Book findBookByBookId(String id) {
		return bookDao.findBookByBookId(id);
	}

	public void deleteByBookId(String bookId) {
		bookDao.deleteByBookId(bookId);
	}

	public void deleteBook(String std) {
		bookDao.deleteById(std);
	}

}
