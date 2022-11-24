package ma.octo.assignement.service.interfaces;

import ma.octo.assignement.dto.CompteRequestDto;
import ma.octo.assignement.dto.CompteResponseDto;
import ma.octo.assignement.exceptions.CompteExisteDejaException;
import ma.octo.assignement.exceptions.CompteNonValidException;
import ma.octo.assignement.exceptions.UtilisateurNonExistantException;

import java.util.List;

public interface CompteService {

    CompteResponseDto getCompte(String nrCompte);


    List<CompteResponseDto> loadAllCompte();

    CompteResponseDto save(CompteRequestDto compteRequestDto) throws UtilisateurNonExistantException, CompteNonValidException, CompteExisteDejaException;
    void deleteCompte(String nrCompte);
}
