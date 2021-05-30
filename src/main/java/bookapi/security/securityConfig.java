package bookapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/* tells it is a configurtaion*/
@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter{

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception
		{
			auth.inMemoryAuthentication()
			.withUser("blah")
			.password("blah")
			.roles("USER")
			.and()
			.withUser("foo")
			.password("foo")
			.roles("ADMIN");
		}
		
		@Bean
		public PasswordEncoder getPasswordEncoder()
		{
			return new BCryptPasswordEncoder();
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception
		{
			/* /** mention all paths any nested level below this  */
			/* 
			 * permitAll() is to allow to everything
			 * more restrict route is placed in top
			 * */
			http.authorizeRequests()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/user").hasRole("USER")
			.antMatchers("/").permitAll()
			
			.and()
			.formLogin();
		}
		
}
