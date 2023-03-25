package net.javaguides.springboot.controller;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguides.springboot.entity.Book;
import net.javaguides.springboot.entity.Student;
import net.javaguides.springboot.service.BookService;
import net.javaguides.springboot.service.StudentService;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	StudentService studentService;

	@Autowired
	BookService bookService;

	//CREATE  THE BOOK OBJECT  THE PARTICULAR STUDENT  & SENT TO THE FONT CONTROLLER
	@GetMapping("/addbook/{id}")
	public String addBook(@PathVariable String id, Model model) {
		List<Student> list = studentService.findByRegdId(id);
		Book b = new Book();

		model.addAttribute("std", list);
		model.addAttribute("book", b);
		return "book/addbook";
	}

	//SAVE THE BOOK IN DATABASE
	@PostMapping("/savebook")
	public String saveBook(@ModelAttribute("book") Book book) {
		String id = book.getRegdId();
		book.setStudent(book.getRegdId());
		System.out.println(book.getRegdId());
		Date date = new Date();
		book.setIssuDateTime(date.toString());
		bookService.saveBook(book);
		return "redirect:/book/booklist/" + id;
	}

	@GetMapping("/booklist/{id}")
	public String bookList(@PathVariable String id, Model model) {
		List<Book> books = bookService.findBook(id);
		model.addAttribute("book", books);
		model.addAttribute("std", studentService.findByRegdId(id));
		return "book/booklist";
	}

	//RETURN THE BOOK
	@GetMapping("/bookreturn/{id}")
	public String bookReturn(@PathVariable String id, Model model) {
		Book b = bookService.findBookByBookId(id);
		String foreignid = b.getRegdId();
		System.out.println(b.getBookId());
		Date date = new Date();
		b.setReturnDateTime(date.toString());
		bookService.saveBook(b);
		return "redirect:/book/booklist/" + foreignid;
	}

	//EDIT THE BOOK 
	@GetMapping("/editbook/{id}")
	public String editBook(@PathVariable String id, Model model) {
		Book b = bookService.findBookByBookId(id);
		List<Student> s = studentService.findByRegdId(b.getRegdId());
		model.addAttribute("std", s);
		model.addAttribute("book", b);

		return "book/editbook";
	}

	//EDIT SAVE BOOK
	@PostMapping("/editsavebook/{id}")
	public String editSave(@ModelAttribute("book") Book book, @PathVariable String id) {
		Book b = new Book();
		b.setBookId(book.getBookId());
		b.setBookName(book.getBookName());
		b.setStudent(book.getRegdId());
		b.setRegdId(book.getRegdId());
		b.setIssuDateTime(book.getIssuDateTime());
		b.setReturnDateTime(book.getReturnDateTime());
		bookService.saveBook(b);

		return "redirect:/book/booklist/" + b.getRegdId();
	}

	//DELETE THE PARTICULAR BOOK
	@GetMapping("/deletebook/{id}")
	public String deleteBook(@PathVariable String id) {
		System.out.println(id);
		Book b = bookService.findBookByBookId(id);
		bookService.deleteByBookId(id);
		return "redirect:/book/booklist/" + b.getRegdId();
	}

}
