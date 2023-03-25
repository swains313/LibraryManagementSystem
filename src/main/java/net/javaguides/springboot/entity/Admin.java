package net.javaguides.springboot.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "authorities")
public class Admin {
	
	@Id
	//@GenericGenerator(name = "customegeneeartor", strategy = "net.javaguides.springboot.configuration.StudentIdGenerator" )
	//@GeneratedValue(generator = "customegeneeartor")
	private String regdId;
	private String firstName;
	private String lastName;
	private Long phone;
	@Column(name = "username")
	private String email;
	private String branch;
	private String password;
	private String year;
	private boolean enabled;
	@Column(name = "authority")
	private String role;
	
	@Transient
	private Student student;
	
	public Admin(Student student)
	{
		this.student=student;
	}


	
	
	

	
}
