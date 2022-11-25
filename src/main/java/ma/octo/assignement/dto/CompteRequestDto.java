package ma.octo.assignement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CompteRequestDto implements Serializable {

    private String nrCompte;
    private String rib;
    private BigDecimal solde;
    private String username;


}