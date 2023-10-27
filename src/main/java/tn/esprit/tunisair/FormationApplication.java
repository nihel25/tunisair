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
		System.out.println(output);
	}


	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.ofMegabytes(10)); // Maximum file size
		factory.setMaxRequestSize(DataSize.ofMegabytes(200)); // Maximum total size of files in a single request
		return factory.createMultipartConfig();
	}
}
