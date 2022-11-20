package ma.octo.assignement.dto;

import java.math.BigDecimal;
import java.util.Date;

public class MoneyDepositDto {
    private String nrCompteBeneficiaire;
    private BigDecimal montant;

    public String getNrCompteBeneficiaire() {
        return nrCompteBeneficiaire;
    }

    public void setNrCompteBeneficiaire(String nrCompteBeneficiaire) {
        this.nrCompteBeneficiaire = nrCompteBeneficiaire;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;
}
