package net.javaguides.springboot.configuration;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	protected void filterChain(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username,password from   users where username=?")
				.authoritiesByUsernameQuery("select username, authority from users where username=?")
				.passwordEncoder(passwordEncoder());
	}

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		System.out.println("FGDGADFGADG");
		security.csrf().disable().authorizeHttpRequests()
				.requestMatchers("/admin").hasRole("ADMIN")
				.requestMatchers("/user/**").hasRole("USER").requestMatchers("/login/**").permitAll()
				.requestMatchers("/book/").permitAll().requestMatchers("/static/**").permitAll()
				.requestMatchers("resources/**").permitAll().requestMatchers("/student/register").permitAll()
				.requestMatchers("/login/**").permitAll().requestMatchers("/static/**").permitAll()
				.requestMatchers("/vendor/**").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/showloginpage").loginProcessingUrl("/authenticateTheUser").permitAll()
				.successHandler(customAuthenticationSuccessHandler).and().logout().permitAll().and().exceptionHandling()
				.accessDeniedPage("/student/register");

		return security.build();
	}

	protected void filterChain(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers("/resources/**", "/login/**", "/static/**", "/images/**");

	}

	@Bean
	protected UserDetailsManager userDetailsManager() {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(dataSource);
		return jdbcUserDetailsManager;
	}

}

class PasswordEncoderTest implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return rawPassword.toString().equals(encodedPassword);
	}

}
