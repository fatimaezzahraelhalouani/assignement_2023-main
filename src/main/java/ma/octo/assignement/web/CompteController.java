package ma.octo.assignement.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.service.CompteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController(value = "/comptes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompteController {
    Logger LOGGER = LoggerFactory.getLogger(CompteController.class);
    @Autowired
    private CompteService compteService;
    @GetMapping("listOfAccounts")
    List<Compte> loadAllCompte() {
        return compteService.loadAllCompte();
    }
}
