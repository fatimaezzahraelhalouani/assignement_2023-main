package ma.octo.assignement.mapper;

import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.dto.UtilisateurResponseDto;
import ma.octo.assignement.dto.UtilisateurResquestDto;

public class UtilisateurMapper {


    public static UtilisateurResponseDto map(Utilisateur utilisateur) {

        UtilisateurResponseDto utilisateurResponseDto = new UtilisateurResponseDto();

        utilisateurResponseDto.setUsername(utilisateur.getUsername());
        utilisateurResponseDto.setFirstname(utilisateur.getFirstname());
        utilisateurResponseDto.setLastname(utilisateur.getLastname());
        utilisateurResponseDto.setGender(utilisateur.getGender());
        utilisateurResponseDto.setBirthdate(utilisateur.getBirthdate());

        return utilisateurResponseDto;

    }
    public static Utilisateur utilisateur(UtilisateurResquestDto utilisateurRequestDto)
    {
        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setFirstname(utilisateurRequestDto.getFirstname());
        utilisateur.setLastname(utilisateurRequestDto.getLastname());
        utilisateur.setGender(utilisateurRequestDto.getGender());
        utilisateur.setBirthdate(utilisateurRequestDto.getBirthdate());
        utilisateur.setUsername(utilisateurRequestDto.getUsername());
        utilisateur.setPassword(utilisateurRequestDto.getPassword());
        return utilisateur;
    }
}
