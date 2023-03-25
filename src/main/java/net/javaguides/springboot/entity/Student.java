package net.javaguides.springboot.entity;

import java.util.List;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
public class Student {

	@Id
	@GenericGenerator(name = "customegeneeartor", strategy = "net.javaguides.springboot.configuration.StudentIdGenerator" )
	@GeneratedValue(generator = "customegeneeartor")
	@Column(unique = true)
	private String regdId;

	private String firstName;
	private String lastName;
	private Long phone;
	@Column(name = "username",unique = true)
	private String email;
	private String branch;
	private String password;
	private String year;
	private boolean enabled;
	@Column(name = "authority")
	private String role;

	//@OneToMany(mappedBy = "student")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Book> book;
	
	@Transient
	private Admin admin;
	
	public Student(Admin admin)
	{
		this.admin=admin;
	}
	
	

	


}
