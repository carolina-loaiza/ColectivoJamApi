package com.cenfotec.ColectivoJamApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ColectivoJamApiApplication {
	
    @Value("${TARGET:World}")
    String target;

    @RestController
    class ColectivoJamApiController {
            @GetMapping("/")
            String hello() {
                    return "Hello " + target + "!";
            }
    }
    
	public static void main(String[] args) {
		SpringApplication.run(ColectivoJamApiApplication.class, args);
	}

}
