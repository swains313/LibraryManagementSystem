package net.javaguides.springboot.entity;


import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Entity
@Data 
public class Book{
	
	
	@Id
	@GenericGenerator(name = "customgenerator",strategy = "net.javaguides.springboot.configuration.BookIdGenerator")
	@GeneratedValue(generator = "customgenerator")
	private String bookId;
	private String regdId;
	private String bookName;
	private String issuDateTime;
	private String returnDateTime;
	
	@JoinColumn(name = "student_id",nullable = false)
	private String student;

}
