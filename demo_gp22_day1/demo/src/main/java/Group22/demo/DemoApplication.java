package Group22.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@SpringBootApplication
@Controller
public class DemoApplication {
	@GetMapping("/")
    public String home() {
        return "redirect:/index.html";//redirect:/index.html
    }

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
