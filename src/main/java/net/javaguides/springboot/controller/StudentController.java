package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguides.springboot.entity.Admin;
import net.javaguides.springboot.entity.Book;
import net.javaguides.springboot.entity.Student;
import net.javaguides.springboot.service.AdminService;
import net.javaguides.springboot.service.BookService;
import net.javaguides.springboot.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@Autowired
	AdminService adminService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	BookService bookService;

	//DISPLAY THE ONE  PARTICULAR HOME PAGE (INDIVIDULALLY)
	@GetMapping("/index/{s}")
	public String indexStudent(@PathVariable String s, Model m) {
		// List<Student> std=studentService.findByRegdId(s);
		Student std = studentService.findByEmail(s);
		m.addAttribute("student", std);
		return "student/index";
	}

//	@GetMapping("/index/{rgd}")
//	public String index(@PathVariable String  rgd,Model m) {
//		//List<Student> std=studentService.findByRegdId(s);
//		List<Student> s=studentService.findByRegdId(rgd);
//		Student std=studentService.findByEmail(s);
//		m.addAttribute("student", std);
//		return "student/index";
//	}

	//DISPLAY THE ABOUT PAGE
	@GetMapping("/about/{id}")
	public String about(@PathVariable String id, Model model) {
		System.out.println("ABOUUT");
		List<Student> std = studentService.findByRegdId(id);
		model.addAttribute("student", std);
		return "student/about";
	}

	//CREATE AN OBJECT FOR STUDENT & SENT TO FRONT CONTROLLER
	@GetMapping("/register")
	public String SignUp(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "register";
	}

	//SAVE THE STUDENT
	@PostMapping("/register")
	public String saveStudent(@ModelAttribute("student") Student student, Admin admin) {
		student.setEnabled(true);
		student.setPassword(passwordEncoder.encode(student.getPassword()));
		student.setRole("STUDENT_ROLE");
		admin.setRole("STUDENT_ROLE");
		student.setAdmin(admin);
		admin.setRegdId(student.getRegdId());
		admin.setEnabled(true);
		studentService.saveStudent(student);
		adminService.saveAdmin(admin);
		return "login";
	}

	//DISPLAY THE BOOKLIST BASED THE USER 
	@GetMapping("/booklist/{id}")
	public String bookList(@PathVariable String id, Model model) {
		List<Book> b = bookService.findBook(id);
		List<Student> std = studentService.findByRegdId(id);
		model.addAttribute("student", std);
		model.addAttribute("book", b);
		return "student/booklist";

	}

	//STUDENT DETAIL ONE PARTICULR
	@GetMapping("/studentdetail/{id}")
	public String studentDetail(@PathVariable String id, Model model) {
		List<Student> std = studentService.findByRegdId(id);
		model.addAttribute("student", std);
		return "student/student-detail";

	}

}
