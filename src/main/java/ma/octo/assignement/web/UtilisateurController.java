package ma.octo.assignement.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.octo.assignement.dto.UtilisateurResponseDto;
import ma.octo.assignement.dto.UtilisateurResquestDto;
import ma.octo.assignement.exceptions.UtilisateurExisteDejaException;
import ma.octo.assignement.service.interfaces.UtlisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    Logger LOGGER = LoggerFactory.getLogger(UtilisateurController.class);


   private final UtlisateurService utlisateurService;
@Autowired
    public UtilisateurController(UtlisateurService utlisateurService) {
        this.utlisateurService = utlisateurService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody UtilisateurResquestDto utilisateurRequestDto) throws UtilisateurExisteDejaException {
        utlisateurService.save(utilisateurRequestDto);
    }

    @GetMapping
    List<UtilisateurResponseDto> loadAllUtilisateur() {
        return utlisateurService.loadAllUtilisateur();
    }
}
