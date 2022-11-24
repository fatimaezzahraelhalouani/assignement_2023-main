package ma.octo.assignement.service.interfaces;

import ma.octo.assignement.dto.UtilisateurResponseDto;
import ma.octo.assignement.dto.UtilisateurResquestDto;
import ma.octo.assignement.exceptions.UtilisateurExisteDejaException;

import java.util.List;

public interface UtlisateurService {
    UtilisateurResponseDto save(
            UtilisateurResquestDto utilisateurResquestDto
    ) throws UtilisateurExisteDejaException;

    List<UtilisateurResponseDto> loadAllUtilisateur();

     UtilisateurResponseDto getUtilisateurByUsername(String username);
}
