package fr.btn.BookMyTicketAPI;

import fr.btn.BookMyTicketAPI.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class BookMyTicketApiApplication {

	public static void main(String[] args) {
	    SpringApplication app = new SpringApplication(BookMyTicketApiApplication.class);
	    app.addListeners(new SwaggerConfiguration());
	    app.run(args);
	}

}
