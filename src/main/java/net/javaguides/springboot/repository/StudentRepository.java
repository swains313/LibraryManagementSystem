package net.javaguides.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import net.javaguides.springboot.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	List<Student> findByRole(String role);

	List<Student> findByRegdId(String regdId);

	@Transactional
	@Modifying
	@Query("delete from Student s where s.regdId=:regdId")
	void deleteById(@Param("regdId") String regdId);

	Student findByEmail(String email);

}
