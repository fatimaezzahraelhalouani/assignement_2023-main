package ma.octo.assignement;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.domain.Transfer;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.repository.TransferRepository;
import ma.octo.assignement.web.TransferController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class NiceBankApplication implements CommandLineRunner {
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private TransferRepository transferRepository;
	@Autowired
	private TransferController transferController;

	public static void main(String[] args) {
		SpringApplication.run(NiceBankApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		Utilisateur utilisateur1 = new Utilisateur();
		utilisateur1.setUsername("user1");
		utilisateur1.setLastname("last1");
		utilisateur1.setFirstname("first1");
		utilisateur1.setGender("Male");

		utilisateurRepository.save(utilisateur1);


		Utilisateur utilisateur2 = new Utilisateur();
		utilisateur2.setUsername("user2");
		utilisateur2.setLastname("last2");
		utilisateur2.setFirstname("first2");
		utilisateur2.setGender("Female");

		utilisateurRepository.save(utilisateur2);

		Compte compte1 = new Compte();
		compte1.setNrCompte("010000A000001000");
		compte1.setRib("RIB1");
		compte1.setSolde(BigDecimal.valueOf(200000L));
		compte1.setUtilisateur(utilisateur1);

		compteRepository.save(compte1);

		Compte compte2 = new Compte();
		compte2.setNrCompte("010000B025001000");
		compte2.setRib("RIB2");
		compte2.setSolde(BigDecimal.valueOf(140000L));
		compte2.setUtilisateur(utilisateur2);

		compteRepository.save(compte2);

		Transfer transfer = new Transfer();
		transfer.setMontantTransfer(BigDecimal.TEN);
		transfer.setCompteBeneficiaire(compte2);
		transfer.setCompteEmetteur(compte1);
		transfer.setDateExecution(new Date());
		transfer.setMotifTransfer("Assignment 2021");

		transferRepository.save(transfer);
		/*TransferDto transferDto = new TransferDto();
		transferDto.setDate(transfer.getDateExecution());
		transferDto.setMotif(transfer.getMotifTransfer());
		transferDto.setMontant(transfer.getMontantTransfer());
		transferDto.setNrCompteEmetteur(transfer.getCompteEmetteur().toString());
		transferDto.setNrCompteBeneficiaire(transfer.getCompteBeneficiaire().toString());
transferController.createTransaction(transferDto);*/
	}
}