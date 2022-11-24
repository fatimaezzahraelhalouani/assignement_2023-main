package ma.octo.assignement.mapper;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.MoneyDeposit;
import ma.octo.assignement.dto.MoneyDepositDto;

public class MoneyDepositMapper {
    private static MoneyDepositDto moneyDepositDto;
    public static MoneyDepositDto map(MoneyDeposit moneyDeposit)
    {
        moneyDepositDto.setNomPrenomEmetteur(moneyDeposit.getNomPrenomEmetteur());
        moneyDepositDto.setMontant(moneyDeposit.getMontantDeposit());
        moneyDepositDto.setMotif(moneyDeposit.getMotifDeposit());
        moneyDepositDto.setDate(moneyDeposit.getDateExecution());
        moneyDepositDto.setRib(moneyDeposit.getCompteBeneficiaire().getRib());
        return moneyDepositDto;
    }

    public static MoneyDeposit moneyDeposit(MoneyDepositDto moneyDepositDto, Compte compteBeneficiaiare) {
        MoneyDeposit moneyDeposit = new MoneyDeposit();

        moneyDeposit.setMotifDeposit(moneyDepositDto.getMotif());
        moneyDeposit.setMontantDeposit(moneyDepositDto.getMontant());
        moneyDeposit.setCompteBeneficiaire(compteBeneficiaiare);
        moneyDeposit.setDateExecution(moneyDepositDto.getDate());
        moneyDeposit.setNomPrenomEmetteur(moneyDepositDto.getNomPrenomEmetteur());

        return moneyDeposit;
    }
}
