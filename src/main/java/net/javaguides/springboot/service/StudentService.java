package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.dao.StudentDao;
import net.javaguides.springboot.entity.Student;

@Service
public class StudentService {

	@Autowired
	StudentDao dao;

	public Student saveStudent(Student student) {
		return dao.saveStudent(student);
	}

	public List<Student> findByRole(String role) {
		return dao.findByRole(role);
	}

	public List<Student> findByRegdId(String id) {
		return dao.findByRegdId(id);
	}

	public void deleteStudent(String std) {
		dao.deleteById(std);
	}

	public Student findByEmail(String email) {
		return dao.findByEmail(email);
	}

}
