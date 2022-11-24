package ma.octo.assignement.Tests;

import ma.octo.assignement.dto.UtilisateurResponseDto;
import ma.octo.assignement.dto.UtilisateurResquestDto;
import ma.octo.assignement.exceptions.UtilisateurExisteDejaException;
import ma.octo.assignement.service.interfaces.UtlisateurService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UtilisateurTest {
    public final UtlisateurService utlisateurService;
@Autowired
    public UtilisateurTest(UtlisateurService utlisateurService) {
        this.utlisateurService = utlisateurService;
    }

    @Test
    public void creerUtilisateur() throws UtilisateurExisteDejaException {
        UtilisateurResquestDto utilisateurResquestDto = new UtilisateurResquestDto();
        utilisateurResquestDto.setUsername("Fati");
        utilisateurResquestDto.setFirstname("Fatima-ezzahra");
        utilisateurResquestDto.setLastname("EL HALOUANI");
        utilisateurResquestDto.setGender("FEMALE");
        utilisateurResquestDto.setPassword("pas");

        UtilisateurResponseDto utilisateurResponseDto = utlisateurService.save(utilisateurResquestDto);

        assertEquals(utilisateurResponseDto,utlisateurService.getUtilisateurByUsername("Fati"));
    }

    @Test
    public void getAllUsers() throws UtilisateurExisteDejaException {
        UtilisateurResquestDto utilisateurResquestDto1 = new UtilisateurResquestDto();
        utilisateurResquestDto1.setUsername("Fatii");
        utilisateurResquestDto1.setFirstname("Fatima-ezzahra");
        utilisateurResquestDto1.setLastname("EL HALOUANI");
        utilisateurResquestDto1.setGender("FEMALE");
        utilisateurResquestDto1.setPassword("pas");

        UtilisateurResponseDto utilisateurResponseDto1 = utlisateurService.save(utilisateurResquestDto1);

        UtilisateurResquestDto utilisateurResquestDto2 = new UtilisateurResquestDto();
        utilisateurResquestDto2.setUsername("doha");
        utilisateurResquestDto2.setFirstname("doha");
        utilisateurResquestDto2.setLastname("Meskour");
        utilisateurResquestDto2.setGender("FEMALE");
        utilisateurResquestDto2.setPassword("pas");

        UtilisateurResponseDto utilisateurResponseDto2 = utlisateurService.save(utilisateurResquestDto2);

        UtilisateurResquestDto utilisateurResquestDto3 = new UtilisateurResquestDto();
        utilisateurResquestDto3.setUsername("najat");
        utilisateurResquestDto3.setFirstname("najat");
        utilisateurResquestDto3.setLastname("amdah");
        utilisateurResquestDto3.setGender("FEMALE");
        utilisateurResquestDto3.setPassword("pas");

        UtilisateurResponseDto utilisateurResponseDto3 = utlisateurService.save(utilisateurResquestDto3);

        List<UtilisateurResponseDto> users = List.of(utilisateurResponseDto1,utilisateurResponseDto2,utilisateurResponseDto3);

        assertEquals(users,utlisateurService.loadAllUtilisateur());

    }
}
