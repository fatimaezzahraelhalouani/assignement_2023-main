package ma.octo.assignement.Tests;

import ma.octo.assignement.dto.CompteRequestDto;
import ma.octo.assignement.dto.CompteResponseDto;
import ma.octo.assignement.dto.UtilisateurResponseDto;
import ma.octo.assignement.dto.UtilisateurResquestDto;
import ma.octo.assignement.exceptions.CompteExisteDejaException;
import ma.octo.assignement.exceptions.CompteNonValidException;
import ma.octo.assignement.exceptions.UtilisateurExisteDejaException;
import ma.octo.assignement.exceptions.UtilisateurNonExistantException;
import ma.octo.assignement.service.interfaces.CompteService;
import ma.octo.assignement.service.interfaces.UtlisateurService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest

public class CompteTest {

   private final UtlisateurService utlisateurService;
    private final CompteService compteService;
@Autowired
    public CompteTest(UtlisateurService utlisateurService, CompteService compteService) {
        this.utlisateurService = utlisateurService;
        this.compteService = compteService;
    }


    @Test
    public void creerCompte() throws UtilisateurExisteDejaException, CompteNonValidException, UtilisateurNonExistantException, CompteExisteDejaException {
        //on cree utilisateur1 tout dabord
        UtilisateurResquestDto utilisateurResquestDto = new UtilisateurResquestDto();
        utilisateurResquestDto.setUsername("Fati");
        utilisateurResquestDto.setFirstname("Fatima-ezzahra");
        utilisateurResquestDto.setLastname("EL HALOUANI");
        utilisateurResquestDto.setGender("FEMALE");
        utilisateurResquestDto.setPassword("pas");

        UtilisateurResponseDto utilisateurResponseDto = utlisateurService.save(utilisateurResquestDto);

        //on cree compte1
        CompteRequestDto compteRequestDto = new CompteRequestDto();
        compteRequestDto.setNrCompte("123456789012");
        compteRequestDto.setRib("123456789012345678901234");
        compteRequestDto.setSolde(BigDecimal.valueOf(200.0).setScale(2, RoundingMode.HALF_DOWN));
        compteRequestDto.setUsername(utilisateurResponseDto.getUsername());

        CompteResponseDto compteResponseDto = compteService.save(compteRequestDto);

        assertEquals("123456789012345678901234",compteResponseDto.getRib());
        assertEquals(compteResponseDto,compteService.getCompte(compteRequestDto.getNrCompte()));
        assertThat(compteResponseDto).isEqualTo(compteService.getCompte(compteRequestDto.getNrCompte()));
        compteService.deleteCompte("123456789012");

    }
    @Test
    public void getAllComptes() throws UtilisateurExisteDejaException, CompteNonValidException, UtilisateurNonExistantException, CompteExisteDejaException {
        //on cree utilisateur1 tout dabord
        UtilisateurResquestDto utilisateurResquestDto1 = new UtilisateurResquestDto();
        utilisateurResquestDto1.setUsername("Fatii");
        utilisateurResquestDto1.setFirstname("Fatima-ezzahra");
        utilisateurResquestDto1.setLastname("EL HALOUANI");
        utilisateurResquestDto1.setGender("FEMALE");
        utilisateurResquestDto1.setPassword("pas");

        UtilisateurResponseDto utilisateurResponseDto1 = utlisateurService.save(utilisateurResquestDto1);

        //on cree compte1
        CompteRequestDto compteRequestDto1 = new CompteRequestDto();
        compteRequestDto1.setNrCompte("123956789012");
        compteRequestDto1.setRib("123456089012345678901234");
        compteRequestDto1.setSolde(BigDecimal.valueOf(200.0).setScale(2, RoundingMode.HALF_DOWN));
        compteRequestDto1.setUsername(utilisateurResponseDto1.getUsername());

        CompteResponseDto compteResponseDto1 = compteService.save(compteRequestDto1);


        //on cree utilisateur2 tout dabord
        UtilisateurResquestDto utilisateurResquestDto2 = new UtilisateurResquestDto();
        utilisateurResquestDto2.setUsername("najat");
        utilisateurResquestDto2.setFirstname("najat");
        utilisateurResquestDto2.setLastname("amdah");
        utilisateurResquestDto2.setGender("FEMALE");
        utilisateurResquestDto2.setPassword("password");

        UtilisateurResponseDto utilisateurResponseDto2 = utlisateurService.save(utilisateurResquestDto2);

        //on cree compte2
        CompteRequestDto compteRequestDto2 = new CompteRequestDto();
        compteRequestDto2.setNrCompte("123457489012");
        compteRequestDto2.setRib("123456789012335678901234");
        compteRequestDto2.setSolde(BigDecimal.valueOf(205.0).setScale(2, RoundingMode.HALF_DOWN));
        compteRequestDto2.setUsername(utilisateurResponseDto2.getUsername());

        CompteResponseDto compteResponseDto2 = compteService.save(compteRequestDto2);

        //on cree utilisateur3 tout dabord
        UtilisateurResquestDto utilisateurResquestDto3 = new UtilisateurResquestDto();
        utilisateurResquestDto3.setUsername("doha");
        utilisateurResquestDto3.setFirstname("doha");
        utilisateurResquestDto3.setLastname("meskour");
        utilisateurResquestDto3.setGender("FEMALE");
        utilisateurResquestDto3.setPassword("passwo");

        UtilisateurResponseDto utilisateurResponseDto3 = utlisateurService.save(utilisateurResquestDto3);

        //on cree compte3
        CompteRequestDto compteRequestDto3 = new CompteRequestDto();
        compteRequestDto3.setNrCompte("123457489082");
        compteRequestDto3.setRib("123456789012335678981234");
        compteRequestDto3.setSolde(BigDecimal.valueOf(105.0).setScale(2, RoundingMode.HALF_DOWN));
        compteRequestDto3.setUsername(utilisateurResponseDto3.getUsername());

        CompteResponseDto compteResponseDto3 = compteService.save(compteRequestDto3);

        List<CompteResponseDto> compteResponseDtos = List.of(compteResponseDto1,compteResponseDto2,compteResponseDto3);


        assertEquals(compteResponseDtos,compteService.loadAllCompte());

    }
}
