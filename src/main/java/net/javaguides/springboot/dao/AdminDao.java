package net.javaguides.springboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.entity.Admin;
import net.javaguides.springboot.repository.AdminRepository;

@Repository
public class AdminDao {
	
	
	
	@Autowired
	AdminRepository repository;
	
	public Admin saveAdmin(Admin admin)
	{
		return repository.save(admin);
	}
	
	
	public List<Admin> findByRole(String role)
	{
		return repository.findByRole(role);
	}
	
	public Admin findById(String st)
	{
		return repository.findByRegdId(st);
	}
	
//	public void updateAdmin(Admin admin)
//	{
//		repository.updateByID(
//			admin.getFirstName(),
//				admin.getLastName(), 
//				admin.getPhone(),
//				admin.getEmail(),
//				admin.getBranch(),
//				admin.getPassword(),
//				admin.getYear(),
//				true,
//				admin.getRole(),
//				admin.getRegdId());
//	}
	
	
	public void deleteById(String std) {
		repository.deleteById(std);
	}

}
