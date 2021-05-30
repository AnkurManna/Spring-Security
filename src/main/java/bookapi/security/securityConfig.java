package bookapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/* tells it is a configurtaion */
@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter{

		@Autowired
		UserDetailsService userDetailsService;
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception
		{
			auth.userDetailsService(userDetailsService);
		}
		
		@SuppressWarnings("deprecation")
		@Bean
		public PasswordEncoder getPasswordEncoder()
		{
			return NoOpPasswordEncoder.getInstance();
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
