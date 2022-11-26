package ma.octo.assignement;


import ma.octo.assignement.dto.UtilisateurResquestDto;
import ma.octo.assignement.service.interfaces.UtlisateurService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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

			UtilisateurResquestDto admin = UtilisateurResquestDto.builder()
					.firstname("El halouani")
					.lastname("Fatimaezzahra")
					.gender("FEMALE")
					.username("fati")
					.password("1234")
					.build();
			utilisateurService.save(admin);

			UtilisateurResquestDto utilisateur = UtilisateurResquestDto.builder()
					.firstname("Doha")
					.lastname("Meskour")
					.gender("FEMALE")
					.username("doha")
					.password("1234")
					.build();
			utilisateurService.save(utilisateur);

		};
	}
	}
