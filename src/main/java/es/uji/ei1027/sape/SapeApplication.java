package es.uji.ei1027.sape;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import es.uji.ei1027.sape.dao.DataInsertDAO;

@SpringBootApplication
public class SapeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SapeApplication.class, args);
	}
}
