package ma.octo.assignement.Tests;

import ma.octo.assignement.dto.*;
import ma.octo.assignement.exceptions.*;
import ma.octo.assignement.service.interfaces.CompteService;
import ma.octo.assignement.service.interfaces.TransferService;
import ma.octo.assignement.service.interfaces.UtlisateurService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Date;

@SpringBootTest
public class TransferTest {
    public final TransferService transferService;
    public final UtlisateurService utilisateurService;
    public final CompteService compteService;

@Autowired
    public TransferTest(TransferService transferService, UtlisateurService utilisateurService, CompteService compteService) {
        this.transferService = transferService;
        this.utilisateurService = utilisateurService;
        this.compteService = compteService;
    }
@Test
        public void creerTransfert() throws UtilisateurExisteDejaException, CompteNonValidException, UtilisateurNonExistantException, CompteExisteDejaException, AuditNonValideException, TransactionException, CompteNonExistantException {
        //creer l'emetteur
    UtilisateurResquestDto utilisateurResquestDto1 = new UtilisateurResquestDto();
    utilisateurResquestDto1.setUsername("badereddine");
    utilisateurResquestDto1.setFirstname("badereddine");
    utilisateurResquestDto1.setLastname("amine");
    utilisateurResquestDto1.setGender("MALE");
    utilisateurResquestDto1.setPassword("password");

    UtilisateurResponseDto utilisateurEmetteur = utilisateurService.save(utilisateurResquestDto1);

    CompteRequestDto compteRequestDto1 = new CompteRequestDto();
    compteRequestDto1.setNrCompte("123456789012");
    compteRequestDto1.setRib("123456789012345678901234");
    compteRequestDto1.setSolde(BigDecimal.valueOf(10000.0).setScale(2, RoundingMode.HALF_DOWN));
    compteRequestDto1.setUsername(utilisateurEmetteur.getUsername());

    CompteResponseDto compteEmetteur = compteService.save(compteRequestDto1);


    //creer le beneficiaire
    UtilisateurResquestDto utilisateurResquestDto2 = new UtilisateurResquestDto();
    utilisateurResquestDto2.setUsername("mostapha");
    utilisateurResquestDto2.setFirstname("mostapha");
    utilisateurResquestDto2.setLastname("elhalouani");
    utilisateurResquestDto2.setGender("MALE");
    utilisateurResquestDto2.setPassword("password");

    UtilisateurResponseDto utilisateurBeneficiaire = utilisateurService.save(utilisateurResquestDto2);

    CompteRequestDto compteRequestDto2 = new CompteRequestDto();
    compteRequestDto2.setNrCompte("123456709012");
    compteRequestDto2.setRib("123456784012345678901234");
    compteRequestDto2.setSolde(BigDecimal.valueOf(20000.0).setScale(2, RoundingMode.HALF_DOWN));
    compteRequestDto2.setUsername(utilisateurBeneficiaire.getUsername());

    CompteResponseDto compteBeneficiaire = compteService.save(compteRequestDto2);

    TransferDto transferDto = new TransferDto();
    transferDto.setNrCompteEmetteur(compteEmetteur.getNrCompte());
    transferDto.setNrCompteBeneficiaire(compteBeneficiaire.getNrCompte());
    transferDto.setMontant(BigDecimal.valueOf(600).setScale(2, RoundingMode.HALF_DOWN));
    transferDto.setDate(Date.from(Instant.now()));
    transferDto.setMotif("service");


    transferService.createTransaction(transferDto);
    //
    CompteResponseDto compteEmmetteur = compteService.getCompte("123456789012");
    CompteResponseDto compteBeneeficiaire = compteService.getCompte("123456709012");
    assertEquals(BigDecimal.valueOf(9400).setScale(2, RoundingMode.HALF_DOWN),compteEmmetteur.getSolde());
    assertEquals(BigDecimal.valueOf(20600).setScale(2, RoundingMode.HALF_DOWN),compteBeneeficiaire.getSolde());

}
}
