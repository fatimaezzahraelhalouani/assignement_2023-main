package ma.octo.assignement.mapper;

import ma.octo.assignement.domain.Transfer;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.dto.UtilisateurDto;

public class UtilisateurMapper {
    private static UtilisateurDto utilisateurDto;

    public static UtilisateurDto map(Utilisateur utilisateur) {
        utilisateurDto = new UtilisateurDto();
        utilisateurDto.setUsername(utilisateur.getUsername());
        utilisateurDto.setFirstname(utilisateur.getFirstname());
        utilisateurDto.setLastname(utilisateur.getLastname());

        return utilisateurDto;

    }
}
