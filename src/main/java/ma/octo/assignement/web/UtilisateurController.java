package ma.octo.assignement.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/utilisateurs")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurController {

    Logger LOGGER = LoggerFactory.getLogger(UtilisateurController.class);

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("lister_utilisateurs")
    List<Utilisateur> loadAllUtilisateur() {
        return utilisateurService.loadAllUtilisateur();
    }
}
