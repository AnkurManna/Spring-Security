package bookapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BookController {

	@GetMapping("/")
	public  String welcome()
	{
		//logger.trace("In hello world");
		//different levels of logging like trace,info
		//log level for package is defined in application.properties 
		return "Hello World";
	}
	
	@GetMapping("/xx")
	public String hello1()
	{
		return "<h1>Hello</h1>";
	}
	
	@GetMapping("/admin")
	public String admin()
	{
		return "Admin";
	}
	
	@GetMapping("/user")
	public String user()
	{
		return "User";
	}
}
