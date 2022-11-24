package ma.octo.assignement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoneyDepositDto {
    private String nrCompteBeneficiaire;
    private BigDecimal montant;
    private Date date;
    private String motif;
    private String nomPrenomEmetteur;
    private String rib;

}
