package com.dprsnn.UtilPlast;

import com.dprsnn.UtilPlast.models.Person;
import com.dprsnn.UtilPlast.repositories.PeopleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class UtilPlastApplication {
	private final PasswordEncoder passwordEncoder;
	private final PeopleRepository peopleRepository;

    public UtilPlastApplication(PasswordEncoder passwordEncoder, PeopleRepository peopleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.peopleRepository = peopleRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(UtilPlastApplication.class, args);
	}

	@PostConstruct
	private void createAdmin(){
		if (peopleRepository.findAllByRole("ROLE_ADMIN").isEmpty()){
			Person person = new Person();
			person.setName("admin");
			person.setSurname("admin");
			person.setPhoneNumber("+38 (000) 000 00-00");
			person.setEmail("admin.email@gmail.com");
			person.setRole("ROLE_ADMIN");

			person.setPassword(passwordEncoder.encode("admin"));
			person.setLastActivity(LocalDate.now().minusMonths(11));
			person.setEnabled(true);
			peopleRepository.save(person);
		}
	}
}
