package ma.octo.assignement;


import ma.octo.assignement.dto.UtilisateurResquestDto;
import ma.octo.assignement.service.interfaces.UtlisateurService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class NiceBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(NiceBankApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner start(UtlisateurService utilisateurService) {
		return args -> {

			//utilisateurService.saveRole(new AppRole(null, "ADMIN"));
			//utilisateurService.saveRole(new AppRole(null, "USER"));

			UtilisateurResquestDto admin = UtilisateurResquestDto.builder()
					.firstname("firstname1")
					.lastname("lastname1")
					.gender("FEMALE")
					.username("admin")
					.password("1234")
					.build();
			utilisateurService.save(admin);

			UtilisateurResquestDto utilisateur = UtilisateurResquestDto.builder()
					.firstname("firstname2")
					.lastname("lastname 2")
					.gender("MALE")
					.username("user")
					.password("1234")
					.build();
			utilisateurService.save(utilisateur);

			//utilisateurService.addRoleToUser("USER","admin" );
			//utilisateurService.addRoleToUser("ADMIN","admin" );

			//utilisateurService.addRoleToUser("USER","user" );
		};
	}
	}
