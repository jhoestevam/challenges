package br.challenge.ximple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = {"br.challenge.ximple", "br.challenge.ximple.infrastracture"})
public class XimpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(XimpleApplication.class, args);
	}

}
