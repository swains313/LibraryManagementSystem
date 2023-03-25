package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.dao.AdminDao;
import net.javaguides.springboot.entity.Admin;

@Service
public class AdminService {

	@Autowired
	AdminDao dao;

	public Admin saveAdmin(Admin admin) {
		return dao.saveAdmin(admin);
	}

	public List<Admin> findByRole(String role) {
		return dao.findByRole(role);
	}

	public Admin findById(String id) {
		return dao.findById(id);
	}

//	public void updateByid(Admin admin)
//	{
//		dao.updateAdmin(admin);
//	}

	public void deleteAdmin(String std) {
		dao.deleteById(std);
	}

}
