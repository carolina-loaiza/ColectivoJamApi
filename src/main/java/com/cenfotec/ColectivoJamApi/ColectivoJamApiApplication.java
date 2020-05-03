package com.cenfotec.ColectivoJamApi;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ColectivoJamApiApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ColectivoJamApiApplication.class, args);
		///Connect.initConnect();
	}
}
