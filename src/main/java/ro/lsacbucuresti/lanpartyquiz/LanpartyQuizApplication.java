package ro.lsacbucuresti.lanpartyquiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class LanpartyQuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(LanpartyQuizApplication.class, args);
	}
}
