package net.javaguides.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import net.javaguides.springboot.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	List<Admin> findByRole(String role);

	Admin findByRegdId(String regdId);

	// @Modifying
	// @Query("update Admin a set a.firstName = ?1, a.lastName = ?2"
	// + ", a.phone=?3, a.email=?4, a.branch=?5, a.password=?6"
	// + ",a.year=?7, a.enabled=?8, a.role=?9 where a.regdId = ?10")
//	Admin updateByID(String firstName,
//			String lastName, 
//			Long phone, 
//			String email,
//			String branch,
//			String password, 
//			String year,
//			boolean enabled, 
//			String role,
//			String regdId);

//	@Transactional
//	@Modifying
//	@Query(value="update users set first_name=?1,last_name=?2,phone=?3,username=?4,branch=?5,password=?6,year=?7,enabled=?8, authority=?9 WHERE regd_id = ?10" ,nativeQuery=true)
//	Void updateByID(@Param("firstName")
//	String firstName,
//	String lastName, 
//	Long phone, 
//	String username, 
//	String branch, 
//	String password,
//	String year, 
//	boolean enabled, 
//	String authority, 
//	String regdId);

	@Transactional
	@Modifying
	@Query("delete from Admin a where a.regdId=:regdId")
	void deleteById(@Param("regdId") String regdId);

}
