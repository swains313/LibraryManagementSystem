package net.javaguides.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import net.javaguides.springboot.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	List<Book> findByStudent(String student);

	Book findByRegdId(String regdId);

	Book findByBookId(String bookId);

	@Transactional
	@Modifying
	@Query("delete from Book b where b.bookId=:bookId")
	void deleteById(@Param("bookId") String bookId);

}
