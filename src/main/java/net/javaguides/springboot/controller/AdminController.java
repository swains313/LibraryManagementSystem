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
import net.javaguides.springboot.entity.Student;
import net.javaguides.springboot.service.AdminService;
import net.javaguides.springboot.service.BookService;
import net.javaguides.springboot.service.StudentService;

//ALL THE ADMIN OPERATION
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@Autowired
	StudentService studentService;

	@Autowired
	BookService bookService;

	@Autowired
	PasswordEncoder passwordEncoder;

	//DISPLAY HOME PAGE
	@GetMapping("/home")
	public String indexAdmin() {
		return "admin/home";
	}

	//DISPLAY ABOUT PAGE
	@GetMapping("/about")
	public String about() {
		return "admin/about";
	}
	

	//DISPLAY ADMIN DETAIL
	@GetMapping("/admin-detail")
	public String adminDetail(Model model) {
		List<Admin> list = adminService.findByRole("ADMIN_ROLE");
		model.addAttribute("admin", list);
		return "admin/admin-detail";
	}

	//ADD ADMIN & CREATE THE OBJECT SENT TO THE FRONT CONTROLLER
	@GetMapping("/add")
	public String addAdmin(Model model) {
		Admin admin = new Admin();
		model.addAttribute("admin", admin);
		return "admin/addadmin";
	}

	//SAVE THE ADMIN IN DATA BASE
	@PostMapping("/saveadmin")
	public String saveAdmin(@ModelAttribute("admin") Admin admin, Student student) {

		student.setRegdId(student.getRegdId());
		student.setBranch("admin");
		student.setEnabled(true);
		student.setRole("ADMIN_ROLE");

		admin.setBranch("admin");
		admin.setEnabled(true);
		admin.setRole("ADMIN_ROLE");
		student.setPassword(passwordEncoder.encode(admin.getPassword()));
		admin.setPassword(student.getPassword());

		studentService.saveStudent(student);
		admin.setRegdId(student.getRegdId());
		adminService.saveAdmin(admin);

		return "redirect:/admin/admin-detail";

	}

	//DISPLAY THE LIST OF STUDENT
	@GetMapping("/student-detail")
	public String findStudent(Model model) {
		List<Student> list = studentService.findByRole("STUDENT_ROLE");
		model.addAttribute("student", list);
		return "admin/student-detail";

	}

	//CREATE THE STUDENT OBJECT AND SENT TO FRONT CONTROLLER
	@GetMapping("/addstudent")
	public String addStudent(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "admin/addstudent";
	}

	//SAVE STUDENT
	@PostMapping("/savestudent")
	public String saveStudents(@ModelAttribute("student") Student student, Admin admin) {
		student.setEnabled(true);
		student.setPassword(passwordEncoder.encode(student.getPassword()));
		student.setRole("STUDENT_ROLE");
		admin.setRole("STUDENT_ROLE");
		student.setAdmin(admin);
		admin.setEnabled(true);
		studentService.saveStudent(student);
		admin.setRegdId(student.getRegdId());
		admin.setPassword(passwordEncoder.encode(student.getPassword()));
		adminService.saveAdmin(admin);

		return "redirect:/admin/student-detail";
	}

	//DELETE THE STUDENT ALONG WITH THERE ALL THE BOOK LIST
	@GetMapping("/deletestudent/{id}")
	public String deleteStudent(@PathVariable String id) {
		System.out.println("delete");
		System.out.println(id);
		System.out.println("delete");
		studentService.deleteStudent(id);
		adminService.deleteAdmin(id);
		bookService.deleteBook(id);
		return "redirect:/admin/student-detail";
	}

	//!!!
	@GetMapping("/editadmin/{id}")
	public String findById(@PathVariable String id, Model model) {
		Admin a = adminService.findById(id);
		model.addAttribute("admin", a);
		return "admin/editadmin";
	}

//	@PostMapping("/esaveadmin/{id}")
//	public String editSave(@ModelAttribute("admin") Admin admin, @PathVariable String id) {
//		Admin a = new Admin();
//		a.setRegdId(admin.getRegdId());
//		a.setFirstName(admin.getFirstName());
//		a.setLastName(admin.getLastName());
//		a.setPhone(admin.getPhone());
//		a.setEmail(admin.getEmail());
//		a.setBranch("admin");
//		a.setPassword(passwordEncoder.encode(admin.getPassword()));
//		a.setYear(admin.getYear());
//		a.setEnabled(true);
//		a.setRole("ADMIN_ROLE");
//
//		Student s = new Student();
//		s.setRegdId(admin.getRegdId());
//		s.setFirstName(admin.getFirstName());
//		s.setLastName(admin.getLastName());
//		s.setPhone(admin.getPhone());
//		s.setEmail(admin.getEmail());
//		s.setBranch("admin");
//		s.setPassword(passwordEncoder.encode(admin.getPassword()));
//		s.setYear(admin.getYear());
//		s.setEnabled(true);
//		s.setRole("ADMIN_ROLE");
//		return "redirect:/admin/admin-detail";
//	}

	//DELETE THE ADMIN
	@GetMapping("/deleteadmin/{id}")
	public String deleteAdmin(@PathVariable("id") String id) {
		studentService.deleteStudent(id);
		adminService.deleteAdmin(id);
		return "redirect:/admin/admin-detail";
	}

}
