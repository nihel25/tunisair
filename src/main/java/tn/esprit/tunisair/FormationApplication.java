package tn.esprit.tunisair;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication

public class FormationApplication  {





	public static void main(String[] args) {
		SpringApplication.run(FormationApplication.class, args);

		String input="howtokill";
		String output = tn.esprit.tunisair.BadWordFilter.getCensoredText(input);

	}


	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.ofMegabytes(10));
		factory.setMaxRequestSize(DataSize.ofMegabytes(200));
		return factory.createMultipartConfig();
	}
}
