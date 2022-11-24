package ma.octo.assignement.mapper;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.dto.CompteRequestDto;
import ma.octo.assignement.dto.CompteResponseDto;

import java.math.BigDecimal;

public class CompteMapper {

//when we want to send an account
    public static CompteResponseDto map(Compte compte){
        CompteResponseDto compteResponseDto = new CompteResponseDto();

        compteResponseDto.setNrCompte(compte.getNrCompte());
        compteResponseDto.setRib(compte.getRib());
        compteResponseDto.setSolde(compte.getSolde());
        compteResponseDto.setUsername(compte.getUtilisateur().getUsername());

        return compteResponseDto;
    }
//when we want to receive an account
    public static Compte RequestCompte(CompteRequestDto compteRequestDto, Utilisateur utilisateur){
        Compte compte = new Compte();

        compte.setNrCompte(compteRequestDto.getNrCompte());
        compte.setRib(compteRequestDto.getRib());
        compte.setSolde(compteRequestDto.getSolde());
        compte.setUtilisateur(utilisateur);

        return compte;
    }
}
