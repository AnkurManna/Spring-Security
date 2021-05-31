package bookapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import bookapi.filters.JwtRequestFilter;

/* tells it is a configurtaion */
@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter{

		@Autowired
		private JwtRequestFilter jwtRequestFilter;
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
			http.csrf().disable()
			.authorizeRequests().antMatchers("/authenticate")
			.permitAll().
			 anyRequest().authenticated()
			.and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			;
			
			http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		}
		
		@Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception{
			
			return super.authenticationManagerBean();
		}
		
		
}
