package ma.octo.assignement.service.validators;

import ma.octo.assignement.dto.CompteRequestDto;
import java.util.function.Function;
import static ma.octo.assignement.service.validators.CompteValidator.IsValid.*;

public interface CompteValidator extends Function<CompteRequestDto, CompteValidator.IsValid>
{

    enum IsValid
    {
        NB_COMPTE_INVALIDE("Numero de compte invalide"),
        RIB_INVALIDE("Numero RIB  invalide"),
        SOLDE_INSUFFISANT(" Solde insuffisant "),
        VALIDE("Tous les champs sont valides");

        private String message;

        IsValid(String message){
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }


    static CompteValidator isNbCompteValid()
    {
        return compteDto -> compteDto.getNrCompte()==null || compteDto.getNrCompte().length() == 0 ?
                NB_COMPTE_INVALIDE : VALIDE;
    }

    static CompteValidator isSoldeSuffisant()
    {
        return compteDto -> compteDto.getSolde().intValue() < 0 ? SOLDE_INSUFFISANT : VALIDE;
    }

    static CompteValidator isRibValid()
    {
        return compteDto -> compteDto.getRib()==null || compteDto.getRib().length() == 0 ?
                RIB_INVALIDE : VALIDE;
    }

    default CompteValidator and (CompteValidator other)
    {
        return compteDto -> {
            IsValid compteValidation = this.apply(compteDto);

            return compteValidation.equals(VALIDE) ? other.apply(compteDto) : compteValidation;
        };
    }

}