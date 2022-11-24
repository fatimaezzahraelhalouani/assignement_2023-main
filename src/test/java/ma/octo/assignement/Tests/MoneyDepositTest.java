package ma.octo.assignement.Tests;

import ma.octo.assignement.dto.*;
import ma.octo.assignement.exceptions.*;
import ma.octo.assignement.service.interfaces.CompteService;
import ma.octo.assignement.service.interfaces.MoneyDepositService;
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
public class MoneyDepositTest {
    public final UtlisateurService utlisateurService;
    public final CompteService compteService;
    public final MoneyDepositService moneyDepositService;

@Autowired
    public MoneyDepositTest(UtlisateurService utlisateurService, CompteService compteService, MoneyDepositService moneyDepositService) {
        this.utlisateurService = utlisateurService;
        this.compteService = compteService;
        this.moneyDepositService = moneyDepositService;
    }
    @Test
    public void creerDeposit() throws UtilisateurExisteDejaException, CompteNonValidException, UtilisateurNonExistantException, CompteExisteDejaException, AuditNonValideException, TransactionException, CompteNonExistantException {
        //creer l'emetteur
        UtilisateurResquestDto utilisateurResquestDto1 = new UtilisateurResquestDto();
        utilisateurResquestDto1.setUsername("badereddine");
        utilisateurResquestDto1.setFirstname("badereddine");
        utilisateurResquestDto1.setLastname("amine");
        utilisateurResquestDto1.setGender("MALE");
        utilisateurResquestDto1.setPassword("password");

        UtilisateurResponseDto utilisateurBeneficiaire = utlisateurService.save(utilisateurResquestDto1);

        CompteRequestDto compteRequestDto1 = new CompteRequestDto();
        compteRequestDto1.setNrCompte("123456789012");
        compteRequestDto1.setRib("123456789012345678901234");
        compteRequestDto1.setSolde(BigDecimal.valueOf(10000.0).setScale(2, RoundingMode.HALF_DOWN));
        compteRequestDto1.setUsername(utilisateurBeneficiaire.getUsername());

        CompteResponseDto compteBeneficiaire = compteService.save(compteRequestDto1);

        MoneyDepositDto moneyDepositDto = new MoneyDepositDto();
        moneyDepositDto.setNrCompteBeneficiaire(compteBeneficiaire.getNrCompte());
        moneyDepositDto.setDate(Date.from(Instant.now()));
        moneyDepositDto.setMotif("dine");
        moneyDepositDto.setMontant(BigDecimal.valueOf(1000));
        moneyDepositDto.setNomPrenomEmetteur("El halouani Fatima-ezzahra");
        moneyDepositDto.setRib("123456789012345678901234");

        moneyDepositService.createDeposit(moneyDepositDto);

        CompteResponseDto compteBenificaire = compteService.getCompte("123456789012");

        assertEquals(BigDecimal.valueOf(11000).setScale(2,RoundingMode.HALF_DOWN),compteBenificaire.getSolde());

    }
}
