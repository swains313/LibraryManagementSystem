package net.javaguides.springboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.entity.Student;
import net.javaguides.springboot.repository.StudentRepository;

@Repository
public class StudentDao {
	
	@Autowired
	StudentRepository repository;
	
	public Student saveStudent(Student student)
	{
		return repository.save(student);
	}
	
	public List<Student> findByRole(String role)
	{
		return repository.findByRole(role);
	}
	
	public List<Student> findByRegdId(String id)
	{
		return repository.findByRegdId(id);
	}
	
	public void deleteById(String std) {
		repository.deleteById(std);
	}
	
	public Student findByEmail(String email) {
		return repository.findByEmail(email);
	}

}
