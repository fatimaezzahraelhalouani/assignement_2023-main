package ma.octo.assignement.web;

import ma.octo.assignement.dto.CompteRequestDto;
import ma.octo.assignement.dto.CompteResponseDto;
import ma.octo.assignement.exceptions.CompteExisteDejaException;
import ma.octo.assignement.exceptions.CompteNonValidException;
import ma.octo.assignement.exceptions.UtilisateurNonExistantException;
import ma.octo.assignement.service.interfaces.CompteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/comptes")
public class CompteController {

    Logger LOGGER = LoggerFactory.getLogger(CompteController.class);


    private final CompteService compteService;
@Autowired
    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }

    @GetMapping
    List<CompteResponseDto> loadAllCompte() {
        return compteService.loadAllCompte();
    }

    @PostMapping
    public CompteResponseDto save(@RequestBody CompteRequestDto compteRequestDto) throws CompteNonValidException, UtilisateurNonExistantException, CompteExisteDejaException {
        return compteService.save(compteRequestDto);
    }


}
